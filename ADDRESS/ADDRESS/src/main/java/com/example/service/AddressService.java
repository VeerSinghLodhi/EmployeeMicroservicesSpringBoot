package com.example.service;

import com.example.dto.AddressDto;
import com.example.dto.AddressRequest;

import java.util.List;

public interface AddressService {

    List<AddressDto> addAddress(AddressRequest addressRequest);

    List<AddressDto> updateAddress(AddressRequest addressRequest);

    AddressDto getAddressById(Long id);

    List<AddressDto>getAllAddress(Long id);

    String deleteAddress(Long id);
}
