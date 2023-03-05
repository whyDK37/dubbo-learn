/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package async;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerAsyncXmlApplication {

  /**
   * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true'
   * before launch the application
   */
  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "async/async-consumer.xml");
    context.start();
    System.out.println("consumer started");

    DemoService service = context.getBean("demoService", DemoService.class);
    String hello = service.sayHello("world");
    System.out.println("result :" + hello);
    Future<String> futureSubtraction = RpcContext.getContext().getFuture();
    System.out.println("future result :" + futureSubtraction.get());
    System.in.read();

  }

}
