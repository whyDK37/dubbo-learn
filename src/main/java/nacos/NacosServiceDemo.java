package nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.pojo.Cluster;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class NacosServiceDemo {
    public static void main(String[] args) throws NacosException, IOException {
        NamingService naming = NamingFactory.createNamingService("10.24.29.7:8848");
        String serviceName = "nacos.test.3";

        Instance instance = new Instance();
        instance.setIp("11.11.11.11");
        instance.setPort(8888);
        instance.setWeight(1.0);
        instance.setClusterName("TEST1");
        instance.setEphemeral(true);// 永久服务

        naming.registerInstance(serviceName, "test", instance);
        naming.subscribe(serviceName, new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("on event," + event.toString());
            }
        });

        System.in.read();
//        Instance instance = new Instance();
//        instance.setIp("55.55.55.55");
//        instance.setPort(9999);
//        instance.setHealthy(false);
//        instance.setWeight(2.0);
//        Map<String, String> instanceMeta = new HashMap<>();
//        instanceMeta.put("site", "et2");
//        instance.setMetadata(instanceMeta);
//
//
//        Service service = new Service("nacos.test.4");
//        service.setAppName("nacos-naming");
//        service.sethealthCheckMode("server");
//        service.setEnableHealthCheck(true);
//        service.setProtectThreshold(0.8F);
//        service.setGroup("CNCF");
//        Map<String, String> serviceMeta = new HashMap<>();
//        serviceMeta.put("symmetricCall", "true");
//        service.setMetadata(serviceMeta);
//        instance.setService(service);
//
//        Cluster cluster = new Cluster();
//        cluster.setName("TEST5");
////        AbstractHealthChecker.Http healthChecker = new AbstractHealthChecker.Http();
////        healthChecker.setExpectedResponseCode(400);
////        healthChecker.setCurlHost("USer-Agent|Nacos");
////        healthChecker.setCurlPath("/xxx.html");
////        cluster.setHealthChecker(healthChecker);
//        Map<String, String> clusterMeta = new HashMap<>();
//        clusterMeta.put("xxx", "yyyy");
//        cluster.setMetadata(clusterMeta);
//
//        instance.setClusterName(cluster.getName());
//
//        naming.registerInstance("nacos.test.4", instance);
    }
}
