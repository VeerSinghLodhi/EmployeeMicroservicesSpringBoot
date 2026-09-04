package com.example.AUTH.controller;


import com.example.AUTH.dto.AddressRequest;
import com.example.AUTH.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?>addAddress(@RequestBody AddressRequest addressRequest){
        return new ResponseEntity<>(addressService.addAddress(addressRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateAddress(@RequestBody AddressRequest addressRequest){
        return new ResponseEntity<>(addressService.updateAddress(addressRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?>getSingleAddress(@PathVariable Long addressId){
        return new ResponseEntity<>(addressService.getAddressById(addressId), HttpStatus.CREATED);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?>getAllAddress(@PathVariable Long id){
        return new ResponseEntity<>(addressService.getAllAddress(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<?>deleteAddress(@PathVariable Long addressId){
        return new ResponseEntity<>(addressService.deleteAddress(addressId), HttpStatus.CREATED);
    }
}
