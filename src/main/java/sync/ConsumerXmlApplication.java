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
package sync;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerXmlApplication {

    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true'
     * before launch the application
     */
    public static void main(String[] args)
            throws InterruptedException, ExecutionException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "sync/consumer.xml");
        context.start();
        System.out.println("consumer started");

        DemoService service = context.getBean("demoService", DemoService.class);
        for (int i = 0; i < 1000; i++) {
            System.out.println("hello " + i);
            try {
                String hello = service.sayHello("world");
                System.out.println("result :" + hello);
            } catch (Exception e) {
                TimeUnit.SECONDS.sleep(5);
                throw new RuntimeException(e);
            }
        }

    }

}
