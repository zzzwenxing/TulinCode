package com.bit.bigdata.time;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NioTimerServer {
	private ServerSocketChannel serverSocketCannel;
	private Selector selector;
	private int port;

	public NioTimerServer(int port) {
		this.port = port;
	}

	public void listener() throws IOException {
		selector = Selector.open();
		serverSocketCannel = ServerSocketChannel.open();
		serverSocketCannel.bind(new InetSocketAddress(port));
		serverSocketCannel.configureBlocking(false);
		serverSocketCannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			// 检查所有已准备就续的键 copy 至 准备注续集
			selector.select(1000);// 没有请求的情况下会阻塞1000 毫秒
			Set<SelectionKey> keys = selector.selectedKeys(); // 获取准备就续键集合
			Iterator<SelectionKey> iterator = keys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				// 处理已准备就续的键
				if (!key.isValid()) {
					key.cancel();
				}
				if (key.isAcceptable()) {
					// 同意建立连接处理
					handAcceptable(key);
				} else if (key.isReadable()) {
					// 读取的处理
					handReader(key);
				} else if (key.isWritable()) {
					// 写入的处理
					handWrite(key);
				}
				iterator.remove(); // remove 出准备就续集
			}
		}
	}

	private void handAcceptable(SelectionKey key) throws IOException {
		SocketChannel socketCannel = ((ServerSocketChannel) key.channel()).accept();
		socketCannel.configureBlocking(false);
		socketCannel.register(selector, SelectionKey.OP_READ);
	}

	private void handReader(SelectionKey key) throws IOException {
		SocketChannel socketCannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(4096);
		int size = socketCannel.read(buffer);
		if (size > 0) {
			buffer.flip();
			byte bytes[] = new byte[buffer.remaining()];
			buffer.get(bytes);
			String content = new String(bytes, "UTF-8");
			// 接收到的命令
			System.out.println("server receive data:" + content);
			buffer.clear();
			key.attach(content);
			socketCannel.register(selector, SelectionKey.OP_WRITE);
		}

	}

	private void handWrite(SelectionKey key) throws IOException {
		SocketChannel socketCannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(4096);
		String currentTime = SimpleDateFormat.getDateTimeInstance().format(new Date());
		buffer.put(currentTime.getBytes("UTF-8"));
		buffer.flip();
		socketCannel.write(buffer);
		buffer.clear();
		key.cancel();
	}

	public static void main(String[] args) throws IOException {
		NioTimerServer server = new NioTimerServer(9090);
		server.listener();
	}
}
