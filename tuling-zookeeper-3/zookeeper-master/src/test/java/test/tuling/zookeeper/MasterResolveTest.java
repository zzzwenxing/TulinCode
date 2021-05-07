package test.tuling.zookeeper;

import com.tuling.zookeeper.master.MasterResolve;
import org.junit.Test;

/**
 * @author Tommy
 * Created by Tommy on 2019/9/23
 **/
public class MasterResolveTest {
//  job 定时任务
    @Test
    public void MasterTest() throws InterruptedException {
        MasterResolve instance = MasterResolve.getInstance();
        System.out.println("master:" + MasterResolve.isMaster());
        Thread.sleep(Long.MAX_VALUE);
    }
}
