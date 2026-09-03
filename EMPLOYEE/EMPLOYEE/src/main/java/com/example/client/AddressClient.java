package com.example.client;

import com.example.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "addressClient", url = "${address.url.service}")
public interface AddressClient {

    @GetMapping("/addresses/all/{id}")
    List<AddressDto> getAllAddress(@PathVariable Long id);
}
