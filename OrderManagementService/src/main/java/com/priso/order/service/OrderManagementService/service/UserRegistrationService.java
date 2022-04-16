package com.priso.order.service.OrderManagementService.service;

import java.util.List;

import com.priso.order.service.OrderManagementService.dto.SellerDto;

/**
 * @author - GreenLearner(https://www.youtube.com/channel/UCaH2MTg94hrJZTolW01a3ZA)
 */
public interface UserRegistrationService {
    String registerSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
