package org.example.service1;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient(name = "servicetwo")
public interface FeignClient {
    @GetMapping("/service21")
    String service21();
}
