package org.example.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2Controller {
    @GetMapping("/service2")
    public String show(){
        return "<h1>Service 2</h1>";
    }

    @GetMapping("/service21")
    public String service21(){
        return "<h1>Service-2 called from Service-1</h1>";
    }
}
