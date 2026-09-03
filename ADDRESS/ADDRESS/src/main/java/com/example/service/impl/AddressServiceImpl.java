package com.example.service.impl;

import com.example.client.EmployeeClient;
import com.example.dto.AddressDto;
import com.example.dto.AddressRequest;
import com.example.dto.AddressRequestDto;
import com.example.dto.EmployeeDto;
import com.example.entity.Address;
import com.example.exception.BadRequestException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AddressMapper;
import com.example.repository.AddressRepository;
import com.example.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;
    private final EmployeeClient employeeClient;

    public AddressServiceImpl(AddressRepository addressRepository, EmployeeClient employeeClient) {
        this.addressRepository = addressRepository;
        this.employeeClient = employeeClient;
    }

    @Override
    public List<AddressDto> addAddress(AddressRequest addressRequest) {
        // checking if employee exits in other service
        employeeClient.getEmployeeById(addressRequest.getEmpId());
//        if(employeeDto==null){
//            throw new ResourceNotFoundException("Employee not found with id: "+addressRequest.getEmpId());
//        }

        List<Address> listToSave = this.saveOrUpdateAddress(addressRequest);
        List<Address>savedAddress = addressRepository.saveAll(listToSave);
        return savedAddress.stream().map(AddressMapper::toDto).toList();
    }

    @Override
    public List<AddressDto> updateAddress(AddressRequest addressRequest) {

        // TODO: check if employee exits
        //addressRequest.getEmpId()
        List<Address> addressList = addressRepository.findAllByEmpId(addressRequest.getEmpId());
        if(addressList.isEmpty()){
            log.info("No address found for employee id {}",addressRequest.getEmpId());
            log.info("Creating address for employee id {}",addressRequest.getEmpId());
        }
        List<Address> listToUpdate = this.saveOrUpdateAddress(addressRequest);
        List<Address>updatedAddress = addressRepository.saveAll(listToUpdate);
        return updatedAddress.stream().map(AddressMapper::toDto).toList();
    }

    @Override
    public AddressDto getAddressById(Long id) {
        if(id==null){
            throw new BadRequestException("Address must not be null");
        }

        Address address = addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address not found with id: "+id)
        );
        return AddressMapper.toDto(address);
    }

    @Override
    public List<AddressDto> getAllAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(AddressMapper::toDto).toList();
    }

    @Override
    public String deleteAddress(Long id) {
        if(id==null){
            throw new BadRequestException("Address must not be null");
        }

        Address address = addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address not found with id: "+id)
        );

        addressRepository.delete(address);
        return "Address has been deleted";
    }


    private List<Address> saveOrUpdateAddress(AddressRequest addressRequest){
        List<Address> listToSave = new ArrayList<>();
        for(AddressRequestDto addressRequestDto : addressRequest.getAddressRequestDtoList()){
            Address address = new Address();
            address.setId(addressRequestDto.getId() != null ? addressRequestDto.getId() : null);
            address.setStreet(addressRequestDto.getStreet());
            address.setPicCode(addressRequestDto.getPicCode());
            address.setCity(addressRequestDto.getCity());
            address.setCountry(addressRequestDto.getCountry());
            address.setAddressType(addressRequestDto.getAddressType());
            address.setEmpId(addressRequest.getEmpId());
            listToSave.add(address);
        }
        return listToSave;
    }
}
