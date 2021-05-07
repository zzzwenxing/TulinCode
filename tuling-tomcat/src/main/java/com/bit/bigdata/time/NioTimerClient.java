package com.bit.bigdata.time;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class NioTimerClient {

	private int port = 9090;

	Selector selector;
	SocketChannel channel;
	CountDownLatch downLatch = new CountDownLatch(1);

	public void send() throws IOException {
		Selector selector = Selector.open();
		channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress(port));
		channel.register(selector, SelectionKey.OP_CONNECT);
		downLatch = new CountDownLatch(1);

		while (true) {
			if (selector.select(100) > 0) {
				Iterator<SelectionKey> set = selector.selectedKeys().iterator();
				while (set.hasNext()) {
					SelectionKey key = set.next();
					set.remove();
					SocketChannel ch = (SocketChannel) key.channel();
					if (key.isConnectable()) {
						ch.register(selector, SelectionKey.OP_WRITE,
								new Integer(1));
						ch.finishConnect();
					} else if (key.isWritable()) {
						key.attach(new Integer(1));
						ch.write(ByteBuffer.wrap((("time")).getBytes()));
						ch.register(selector, SelectionKey.OP_READ,
								new Integer(1));
					} else if (key.isReadable()) {
						key.attach(new Integer(1));
						ByteArrayOutputStream output = new ByteArrayOutputStream();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						int len = 0;
						while ((len = ch.read(buffer)) != 0) {
							buffer.flip();
							byte by[] = new byte[buffer.remaining()];
							buffer.get(by);
							output.write(by);
							buffer.clear();
						}
						System.out.println(new String(output.toByteArray()));
						output.close();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// client();
		new NioTimerClient().send();
	}
}
