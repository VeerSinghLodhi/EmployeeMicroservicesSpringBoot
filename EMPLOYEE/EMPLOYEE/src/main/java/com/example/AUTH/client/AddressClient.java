package com.example.AUTH.client;

import com.example.AUTH.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "addressClient", url = "${address.url.service}")
public interface AddressClient {

    @GetMapping("/addresses/all/{id}")
    List<AddressDto> getAllAddress(@PathVariable Long id);
}
