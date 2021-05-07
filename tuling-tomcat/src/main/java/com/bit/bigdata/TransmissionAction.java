package com.bit.bigdata;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransmissionAction
 */
public class TransmissionAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger("TransmissionAction");

    private EsServiceImpl esService;
    private volatile long count = 0;
    private volatile long fails = 0;
    private volatile long succeeds = 0;
    private ExecutorService threadService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransmissionAction() {
        super();
        threadService = Executors.newCachedThreadPool();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        esService = new EsServiceImpl();
        // 执行统计
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    long newCount = esService.getCount() - count;
                    long tps = newCount / 5;
                    count = esService.getCount();
                    fails = esService.getFails();
                    succeeds = esService.getSucceeds();
                    logger.info(String.format("每秒消费数：%s 总数:%s 成功：%s 失败：%s", tps, count, succeeds, fails));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.setName("bit-statistical");
        t.setDaemon(true);
        t.start();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String data = request.getParameter("data");
        esService.putData(data);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
