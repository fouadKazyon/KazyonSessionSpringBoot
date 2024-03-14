package com.shop.Shop.Service;

import com.shop.Shop.Model.Shop;
import com.shop.Shop.Model.ShopRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ShopService {
    public Optional<Shop> getShopById(int id);
    public List<Shop> getShopsByDistrictId(int districtId);
    public List<Shop> getAllShops();
    public Shop addShop(ShopRequest shopRequest);
    public boolean deleteShopById(int id);

}
