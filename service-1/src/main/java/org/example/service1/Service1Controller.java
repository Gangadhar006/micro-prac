package org.example.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1Controller {

    @Autowired
    private FeignClient feignClient;

    @GetMapping("/service1")
    public String show() {
        String s = feignClient.service21();
        System.out.println(s);
        return "<h1>Service 1 </h1>".concat(s);
    }
}