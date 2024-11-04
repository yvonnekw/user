package com.auction.user.dto;


import lombok.Builder;


@Builder
public record AddressResponse(
        Long userId,
        Long aLong,
        String street,
        String city,
        String state,
        String postalCode,
        String country,
        boolean isBillingAddress,
        boolean isShippingAddress) {

}
