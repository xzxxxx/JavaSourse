/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.debug;

import static org.graalvm.compiler.debug.DebugOptions.PrintGraphHost;
import static org.graalvm.compiler.debug.DebugOptions.PrintGraphPort;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Supplier;

import org.graalvm.compiler.debug.DebugOptions.PrintGraphTarget;
import org.graalvm.compiler.options.OptionValues;

import jdk.vm.ci.common.NativeImageReinitialize;

final class IgvDumpChannel implements WritableByteChannel {
    private final Supplier<Path> pathProvider;
    private final OptionValues options;
    private WritableByteChannel sharedChannel;
    private boolean closed;

    IgvDumpChannel(Supplier<Path> pathProvider, OptionValues options) {
        this.pathProvider = pathProvider;
        this.options = options;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        WritableByteChannel channel = channel();
        return channel == null ? 0 : channel.write(src);
    }

    @Override
    public boolean isOpen() {
        return !closed;
    }

    @Override
    public void close() throws IOException {
    }

    void realClose() throws IOException {
        closed = true;
        if (sharedChannel != null) {
            sharedChannel.close();
            sharedChannel = null;
        }
    }

    WritableByteChannel channel() throws IOException {
        if (closed) {
            throw new IOException();
        }
        if (sharedChannel == null) {
            PrintGraphTarget target = DebugOptions.PrintGraph.getValue(options);
            if (target == PrintGraphTarget.File) {
                sharedChannel = createFileChannel(pathProvider, null);
            } else if (target == PrintGraphTarget.Network) {
                sharedChannel = createNetworkChannel(pathProvider, options);
            } else {
                TTY.println("WARNING: Graph dumping requested but value of %s option is %s", DebugOptions.PrintGraph.getName(), PrintGraphTarget.Disable);
            }
        }
        return sharedChannel;
    }

    private static WritableByteChannel createNetworkChannel(Supplier<Path> pathProvider, OptionValues options) throws IOException {
        String host = PrintGraphHost.getValue(options);
        int port = PrintGraphPort.getValue(options);
        try {
            WritableByteChannel channel = SocketChannel.open(new InetSocketAddress(host, port));
            String targetAnnouncement = String.format("Connected to the IGV on %s:%d", host, port);
            maybeAnnounceTarget(targetAnnouncement);
            return channel;
        } catch (ClosedByInterruptException | InterruptedIOException e) {
            /*
             * Interrupts should not count as errors because they may be caused by a cancelled Graal
             * compilation. ClosedByInterruptException occurs if the SocketChannel could not be
             * opened. InterruptedIOException occurs if new Socket(..) was interrupted.
             */
            return null;
        } catch (IOException e) {
            String networkFailure = String.format("Could not connect to the IGV on %s:%d", host, port);
            if (pathProvider != null) {
                return createFileChannel(pathProvider, networkFailure);
            } else {
                throw new IOException(networkFailure, e);
            }
        }
    }

    @NativeImageReinitialize private static String lastTargetAnnouncement;

    private static void maybeAnnounceTarget(String targetAnnouncement) {
        if (!targetAnnouncement.equals(lastTargetAnnouncement)) {
            // Ignore races - an extra announcement is ok
            lastTargetAnnouncement = targetAnnouncement;
            TTY.println(targetAnnouncement);
        }
    }

    private static WritableByteChannel createFileChannel(Supplier<Path> pathProvider, String networkFailure) throws IOException {
        Path path = pathProvider.get();
        try {
            FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            File dir = path.toFile();
            if (!dir.isDirectory()) {
                dir = dir.getParentFile();
            }
            if (networkFailure == null) {
                maybeAnnounceTarget("Dumping IGV graphs in " + dir);
            } else {
                maybeAnnounceTarget(networkFailure + ". Dumping IGV graphs in " + dir);
            }
            return channel;
        } catch (IOException e) {
            throw new IOException(String.format("Failed to open %s to dump IGV graphs", path), e);
        }
    }

}
