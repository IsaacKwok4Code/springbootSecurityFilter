package com.yin4learn.securityFilter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    
    private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);

    public String serve() {
        logger.info("SimpleService.serve() called");
        return "Service is serving!";
    }

}