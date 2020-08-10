/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


package org.graalvm.compiler.hotspot.meta;

import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.hotspot.GraalHotSpotVMConfig;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.gc.BarrierSet;
import org.graalvm.compiler.nodes.gc.CardTableBarrierSet;
import org.graalvm.compiler.nodes.gc.G1BarrierSet;
import org.graalvm.compiler.nodes.java.AbstractNewObjectNode;
import org.graalvm.compiler.nodes.memory.FixedAccessNode;
import org.graalvm.compiler.nodes.spi.GCProvider;

import jdk.vm.ci.meta.MetaAccessProvider;

public class HotSpotGCProvider implements GCProvider {
    private final BarrierSet barrierSet;

    public HotSpotGCProvider(GraalHotSpotVMConfig config, MetaAccessProvider metaAccess) {
        this.barrierSet = createBarrierSet(config, metaAccess);
    }

    @Override
    public BarrierSet getBarrierSet() {
        return barrierSet;
    }

    private BarrierSet createBarrierSet(GraalHotSpotVMConfig config, MetaAccessProvider metaAccess) {
        boolean useDeferredInitBarriers = config.useDeferredInitBarriers;
        if (config.useG1GC) {
            return new G1BarrierSet(metaAccess) {
                @Override
                protected boolean writeRequiresPostBarrier(FixedAccessNode initializingWrite, ValueNode writtenValue) {
                    if (!super.writeRequiresPostBarrier(initializingWrite, writtenValue)) {
                        return false;
                    }
                    return !useDeferredInitBarriers || !isWriteToNewObject(initializingWrite);
                }
            };
        } else {
            return new CardTableBarrierSet() {
                @Override
                protected boolean writeRequiresBarrier(FixedAccessNode initializingWrite, ValueNode writtenValue) {
                    if (!super.writeRequiresBarrier(initializingWrite, writtenValue)) {
                        return false;
                    }
                    return !useDeferredInitBarriers || !isWriteToNewObject(initializingWrite);
                }
            };
        }
    }

    /**
     * For initializing writes, the last allocation executed by the JVM is guaranteed to be
     * automatically card marked so it's safe to skip the card mark in the emitted code.
     */
    protected boolean isWriteToNewObject(FixedAccessNode initializingWrite) {
        if (!initializingWrite.getLocationIdentity().isInit()) {
            return false;
        }
        // This is only allowed for the last allocation in sequence
        ValueNode base = initializingWrite.getAddress().getBase();
        if (base instanceof AbstractNewObjectNode) {
            Node pred = initializingWrite.predecessor();
            while (pred != null) {
                if (pred == base) {
                    return true;
                }
                if (pred instanceof AbstractNewObjectNode) {
                    initializingWrite.getDebug().log(DebugContext.INFO_LEVEL, "Disallowed deferred init because %s was last allocation instead of %s", pred, base);
                    return false;
                }
                pred = pred.predecessor();
            }
        }
        initializingWrite.getDebug().log(DebugContext.INFO_LEVEL, "Unable to find allocation for deferred init for %s with base %s", initializingWrite, base);
        return false;
    }
}
