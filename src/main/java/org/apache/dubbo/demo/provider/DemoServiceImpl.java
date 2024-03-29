/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.dubbo.demo.provider;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DubboService
public class DemoServiceImpl implements DemoService {

  private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

  @Override
  public String sayHello(String name) {
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    logger.info(
        "Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    return "Hello " + name + ", response from provider: " + RpcContext.getContext()
        .getLocalAddress();
  }

  @Override
  public String timeout(String name) throws InterruptedException {
    logger.info("收到消息，3s 后返回：" + name);
    TimeUnit.SECONDS.sleep(3);
    return name;
  }

  @Override
  public CompletableFuture<String> sayHelloAsync(String name) {
    return CompletableFuture.supplyAsync(new Supplier<String>() {
      @Override
      public String get() {
        return "sayHelloAsync " + name;
      }
    });
  }

}
