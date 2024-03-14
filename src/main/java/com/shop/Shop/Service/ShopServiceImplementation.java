package com.shop.Shop.Service;

import com.shop.Exception.NotFound;
import com.shop.Shop.Model.Shop;
import com.shop.Shop.Model.ShopRequest;
import com.shop.Shop.Repository.ShopRepository;
import com.shop.distrect.Model.District;
import com.shop.distrect.Repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShopServiceImplementation implements ShopService {
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public Optional<Shop> getShopById(int id) {
        return Optional.ofNullable(shopRepository.findById(id).orElseThrow(() ->
                new NotFound("shop not found with id " + id)
        ));
    }

    @Override
    public List<Shop> getShopsByDistrictId(int districtId) {
        List<Shop> shops = shopRepository.findByDistrictId(districtId);

        if (shops.isEmpty()) {
            throw new NotFound("No shops found with district ID: " + districtId);
        }

        return shops;
    }

    @Override
    public List<Shop> getAllShops() {
      return shopRepository.findAll();
    }

    @Override
    public  Shop addShop(ShopRequest shopRequest) {
        District district = districtRepository.findById(shopRequest.getDistrictId())
                .orElseThrow(() ->  new NotFound("shop not found with id " + shopRequest.getDistrictId())
                        );
        Shop newShop = new Shop();
        newShop.setName(shopRequest.getName());
        newShop.setCode(shopRequest.getCode());
        newShop.setDistrict(district);
        // Save the new shop
       return shopRepository.save(newShop);

    }

    @Override
    public boolean deleteShopById(int id) {
        try {
            Optional<Shop> shop = shopRepository.findById(id);
            if(shop.isPresent())
            {
                shopRepository.deleteById(id);
                return true; // Deletion successful
            }
            else {
                return  false;
            }

        } catch (Exception ex) {
            return false;
        }
    }
}
