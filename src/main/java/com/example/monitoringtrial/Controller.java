package com.example.monitoringtrial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

  @Autowired
  CustomerRepository customerRepository;

  Logger logger = LoggerFactory.getLogger(Controller.class.getName());

  @GetMapping("/ping")
  public String ping() {
    logger.info("INVOKED : {}", "/ping");
    return "pong";
  }

  @GetMapping("/customers")
  List<CustomerEntity> getCustomers() {
    logger.info("INVOKED : {}", "/customers");
    return customerRepository.findAll();
  }

  @GetMapping("/customers/transform")
  public List getCustomersTransform()
     throws InterruptedException {
    List customers = customerRepository.findAll();
    logger.info("INVOKED : {}", "/customers/transform");

    Thread.sleep(5000); //slow operation

    return customers;
  }
}
