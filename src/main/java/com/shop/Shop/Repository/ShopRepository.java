package com.shop.Shop.Repository;

import com.shop.Shop.Model.Shop;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {

    @EntityGraph(attributePaths = {"district"})
    Optional<Shop> findById(int id);


    List<Shop> findByDistrictId(int districtId);
}
