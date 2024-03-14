package com.shop.distrect.Service;

import com.shop.distrect.Model.DistrectRequest;
import com.shop.distrect.Model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DistrectService {


    public Optional<District> getDistrictById(int id);
    public Page<District> getAllDistricts(Pageable pageable);
    public District addDistrict(DistrectRequest distrectRequest);
    public boolean deleteDistrictById(int id);

    public District updateDistrict(int id, DistrectRequest distrectRequest);
}
