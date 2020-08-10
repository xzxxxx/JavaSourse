/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */

package jdk.internal.access;

import jdk.internal.access.foreign.MemorySegmentProxy;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public interface JavaNioAccess {
    /**
     * Provides access to information on buffer usage.
     */
    interface BufferPool {
        String getName();
        long getCount();
        long getTotalCapacity();
        long getMemoryUsed();
    }
    BufferPool getDirectBufferPool();

    /**
     * Constructs a direct ByteBuffer referring to the block of memory starting
     * at the given memory address and extending {@code cap} bytes.
     * The {@code ob} parameter is an arbitrary object that is attached
     * to the resulting buffer.
     * Used by {@code jdk.internal.foreignMemorySegmentImpl}.
     */
    ByteBuffer newDirectByteBuffer(long addr, int cap, Object obj, MemorySegmentProxy segment);

    /**
     * Constructs an heap ByteBuffer with given backing array, offset, capacity and segment.
     * Used by {@code jdk.internal.foreignMemorySegmentImpl}.
     */
    ByteBuffer newHeapByteBuffer(byte[] hb, int offset, int capacity, MemorySegmentProxy segment);

    /**
     * Used by {@code jdk.internal.foreign.Utils}.
     */
    Object getBufferBase(ByteBuffer bb);

    /**
     * Used by {@code jdk.internal.foreign.Utils}.
     */
    long getBufferAddress(ByteBuffer bb);

    /**
     * Used by byte buffer var handle views.
     */
    void checkSegment(Buffer buffer);
}
