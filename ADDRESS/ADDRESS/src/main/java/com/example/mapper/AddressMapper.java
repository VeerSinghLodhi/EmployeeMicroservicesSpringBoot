package com.example.mapper;

import com.example.dto.AddressDto;
import com.example.entity.Address;

public class AddressMapper {


    public static AddressDto toDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setEmpId(address.getEmpId());
        addressDto.setStreet(address.getStreet());
        addressDto.setPicCode(address.getPicCode());
        addressDto.setCity(address.getCity());
        addressDto.setCountry(address.getCountry());
        addressDto.setAddressType(address.getAddressType());
        return addressDto;
    }

    public static Address toEntity(AddressDto addressDto){
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setEmpId(addressDto.getEmpId());
        address.setStreet(addressDto.getStreet());
        address.setPicCode(addressDto.getPicCode());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setAddressType(addressDto.getAddressType());
        return address;
    }
}
