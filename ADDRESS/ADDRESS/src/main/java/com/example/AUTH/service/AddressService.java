package com.example.AUTH.service;

import com.example.AUTH.dto.AddressDto;
import com.example.AUTH.dto.AddressRequest;

import java.util.List;

public interface AddressService {

    List<AddressDto> addAddress(AddressRequest addressRequest);

    List<AddressDto> updateAddress(AddressRequest addressRequest);

    AddressDto getAddressById(Long id);

    List<AddressDto>getAllAddress(Long id);

    String deleteAddress(Long id);
}
