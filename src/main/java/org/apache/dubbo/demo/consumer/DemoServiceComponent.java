package org.apache.dubbo.demo.consumer;

import java.util.concurrent.CompletableFuture;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.demo.DemoService;
import org.springframework.stereotype.Component;

@Component("demoServiceComponent")
public class DemoServiceComponent implements DemoService {

  @DubboReference
  private DemoService demoService;

  @Override
  public String sayHello(String name) {
    return demoService.sayHello(name);
  }

  @Override
  public String timeout(String name) throws InterruptedException {
    return demoService.timeout(name);
  }

  @Override
  public CompletableFuture<String> sayHelloAsync(String name) {
    return null;
  }
}