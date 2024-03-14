package com.shop.Shop.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShopRequest {


    @NotBlank(message = "name can not be empty")
    private String name;

    @NotBlank(message = "code can not be empty")
    private String code;

    private int districtId;
}
