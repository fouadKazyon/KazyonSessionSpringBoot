package com.shop.distrect.Repository;

import com.shop.distrect.Model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Integer> {
    
}
