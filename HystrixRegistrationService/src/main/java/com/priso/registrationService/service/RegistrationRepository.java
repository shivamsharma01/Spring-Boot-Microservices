package com.priso.registrationService.service;

import org.springframework.stereotype.Repository;

import com.priso.registrationService.dto.SellerDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationRepository {

    List<SellerDto> sellerDtoList = new ArrayList<>();

    public boolean addSeller(SellerDto sellerDto) {

        return sellerDtoList.add(sellerDto);
    }

    public List<SellerDto> getSellerList() {
        return sellerDtoList;
    }
}
