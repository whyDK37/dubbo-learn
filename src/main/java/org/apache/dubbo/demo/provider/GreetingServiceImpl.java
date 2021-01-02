package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.GreetingService;

/**
 *
 */
public class GreetingServiceImpl implements GreetingService {

  @Override
  public String hello() {
    return "Greetings!";
  }
}
