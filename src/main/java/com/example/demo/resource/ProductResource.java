package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProductResource {
    @Autowired
    private Environment env;
    public String getProductUrl(String productName) {
        return env.getProperty("product-urls." + productName);
    }
}
