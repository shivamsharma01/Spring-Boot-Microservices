package com.priso.registrationService.service;

import java.util.List;

import com.priso.registrationService.dto.SellerDto;

public interface RegistrationService {


    String addSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
