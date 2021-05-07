package com.bit.bigdata;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 收银机模拟系统
 * 
 * @author Administrator
 * 
 */
public class PosSite {
	static Logger loger = Logger.getLogger(PosSite.class.getName());
	private static TradeProduct[] mockProducts;
	private long posNumber;
	private String shopName;
	private volatile long count;// 执行总数
	private volatile long sucess;// 成功总数
	private volatile long fails;// 失败总数
	private Thread thread;// 执行线程

	private ExecutorService pool;
	{
		pool = new ThreadPoolExecutor(200, 200, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1000));

	}

	public PosSite(long posNumber, String shopName) {
		this.posNumber = posNumber;
		this.shopName = shopName;
		pool = Executors.newCachedThreadPool();
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSucess() {
		return sucess;
	}

	public void setSucess(long sucess) {
		this.sucess = sucess;
	}

	public long getFails() {
		return fails;
	}

	public void setFails(long fails) {
		this.fails = fails;
	}

	// 启动
	public void start() {
		// 停止原活动线程
		stop();
		thread = new Thread() {
			private volatile boolean isRun = true;

			@Override
			public void run() {
				while (isRun) {
					// 执行一次收银动作
					// 并发效果
					pool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								executeCashier();
								sucess++;
							} catch (Exception e) {
								e.printStackTrace();
								fails++;
							} finally {
								count++;
							}
						}
					});
					try {
						Thread.sleep(new Random().nextInt(200));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void destroy() {
				isRun = false;
			}
		};
		thread.start();
	}

	// 停止
	public void stop() {
		if (thread != null && thread.isAlive()) {
			thread.destroy();
		}
	}

	// 执行一次收银
	private String executeCashier() throws IOException {
		String data = JSON.toJSONString(mockTradeData(),
				SerializerFeature.DisableCircularReferenceDetect);
		data = URLEncoder.encode(data, "UTF-8");
		// URL url = new
		// URL("http://127.0.0.1:8080/bit-bigdata-transmission/data?data=" +
		// data);
		URL url = new URL("http://127.0.0.1:8080/data");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setReadTimeout(5000);
		conn.setConnectTimeout(5000); // 建立连接 2 写入参数 3读取结果
		// 建立连接
		conn.connect();// 服务已经分配了一个服务线程
		// 写数据
		try {
			// 网络延时
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		OutputStream out = conn.getOutputStream();
		out.write(("data=" + data).getBytes());
		out.flush();
		out.close();
		InputStream input = conn.getInputStream();
		byte[] b = sun.misc.IOUtils.readFully(input, -1, true);
		input.close();
		conn.disconnect();
		return new String(b);
	}

	/**
	 * 模拟交易数据
	 * 
	 * @return
	 */
	private PosTradeRecord mockTradeData() {
		PosTradeRecord node = new PosTradeRecord();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmssSSSS");
		int s = new Random().nextInt(10000);
		node.setOrderId(f.format(new Date()) + s);
		node.setOrderMoney(Long.valueOf(new Random().nextInt(1000000)));
		node.setPosNumber(posNumber);
		node.setShopName(shopName);
		node.setTradeDate(System.currentTimeMillis());
		List<TradeProduct> products = new ArrayList<>();
		int pSize = new Random().nextInt(20) + 1;
		for (int i = 0; i < pSize; i++) {
			products.add(mockProducts[new Random().nextInt(10)]);
		}
		node.setProducts(products);
		return node;
	}

	static {
		mockProducts = new TradeProduct[10];
		mockProducts[0] = new TradeProduct(1, "红牛", 2, 600);
		mockProducts[1] = new TradeProduct(2, "伟哥", 1, 9900);
		mockProducts[2] = new TradeProduct(6, "尿布湿", 3, 5600);
		mockProducts[3] = new TradeProduct(4, "奶粉", 4, 5600);
		mockProducts[4] = new TradeProduct(5, "2B铅笔", 6, 1600);
		mockProducts[5] = new TradeProduct(6, "牛奶", 7, 5600);
		mockProducts[6] = new TradeProduct(7, "电风扇", 8, 172600);
		mockProducts[7] = new TradeProduct(8, "牛肉", 8, 4600);
		mockProducts[8] = new TradeProduct(9, "纸巾", 9, 300);
		mockProducts[9] = new TradeProduct(10, "杯子", 10, 4600);
	}

	public static void main(String[] args) throws IOException {
		List<PosSite> sites = new ArrayList<>();
		int initStartCount = Integer.parseInt(args.length > 0 ? args[0] : "5");
		for (int i = 0; i < initStartCount; i++) {
			PosSite p = new PosSite(i, "沃乐码解放路店");
			sites.add(p);
			p.start();
		}
		long count = 0;
		long sucess = 0;
		long fails = 0;

		while (true) {
			long newCount = 0, newSucess = 0, newfails = 0;
			for (PosSite p : sites) {
				newCount += p.getCount();
				newSucess += p.getSucess();
				newfails += p.getFails();
			}
			long tps = (newCount - count) / 2;
			count = newCount;
			sucess = newSucess;
			fails = newfails;

			loger.info(String.format("每秒提交数：%s 总数:%s 成功：%s 失败：%s", tps, count,
					sucess, fails));
			try {// 休眠两秒
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
