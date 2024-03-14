package com.shop.Shop.Controller;

import com.shop.Shop.Model.Response;
import com.shop.Shop.Model.Shop;
import com.shop.Shop.Model.ShopRequest;
import com.shop.Shop.Service.ShopService;
import com.shop.Shop.Service.ShopServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    ShopService shopService;


    @GetMapping("/{id}")
    public ResponseEntity<Response> getShopById(@PathVariable int id) {
        Optional<Shop> shop = shopService.getShopById(id);
        Response response = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("Shop retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(shop )
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<Response> getAllShops(@RequestParam(required = false) int distrectId) {
        List<Shop> shops;
        if (distrectId > 0) {
            // If storeId is provided, filter shops by store
            shops = shopService.getShopsByDistrictId(distrectId);

        } else {
            // If no storeId provided, retrieve all shops
            shops = shopService.getAllShops();
        }


        Response response = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("All shops retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(shops)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> createShop(@Valid @RequestBody ShopRequest shopRequest) {
        Shop shop = shopService.addShop(shopRequest);

        Response response = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("Shop added successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .data(shop)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteShopById(@PathVariable int id) {
        boolean isDeleted = shopService.deleteShopById(id);
        if (isDeleted) {

            Response response = Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("shop Deleted successfully")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            Response response = Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("shop ID not found")
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
