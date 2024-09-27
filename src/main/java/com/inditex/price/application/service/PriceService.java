package com.inditex.price.application.service;

import org.springframework.stereotype.Service;

import com.inditex.price.application.dto.PriceRequest;
import com.inditex.price.application.dto.PriceResponse;
import com.inditex.price.application.mapper.PriceMapper;
import com.inditex.price.domain.repository.PriceRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;
    
    /**
     * Selects the price for a given product and brand based on the application date.
     *
     * @param productId      The ID of the product
     * @param brandId       The ID of the brand
     * @param applicationDate The date when the price is to be applied
     * @return A Mono containing the selected Price or an error if not found
     */
    public Mono<PriceResponse> selectPrice(PriceRequest priceRequest) {
        
        return priceRepository.findSelectPrice(priceRequest.getProductId(), priceRequest.getBrandId(), priceRequest.getApplicationDate())
        		.map(price -> PriceMapper.INSTANCE.toPriceResponse(price));
    }
}
