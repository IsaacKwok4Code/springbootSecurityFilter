package com.yin4learn.securityFilter.control;

import com.yin4learn.securityFilter.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private SimpleService simpleService;


    @GetMapping("/hello")
    public String hello() {
        logger.info("Hello endpoint is invoked.");
        return simpleService.serve();
    }

}
