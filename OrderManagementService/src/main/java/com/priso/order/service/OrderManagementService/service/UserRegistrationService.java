package com.priso.order.service.OrderManagementService.service;

import java.util.List;

import com.priso.order.service.OrderManagementService.dto.SellerDto;

public interface UserRegistrationService {
    String registerSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
