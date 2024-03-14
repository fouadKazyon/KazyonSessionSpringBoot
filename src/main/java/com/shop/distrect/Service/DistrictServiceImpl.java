package com.shop.distrect.Service;

import com.shop.Exception.NotFound;
import com.shop.distrect.Model.DistrectRequest;
import com.shop.distrect.Model.District;
import com.shop.distrect.Repository.DistrictRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrectService {

    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public Optional<District> getDistrictById(int id) {

        return Optional.ofNullable(districtRepository.findById(id).orElseThrow(() ->
                new NotFound("district not found with id " + id)
        ));
    }


    @Override
    public Page<District> getAllDistricts(Pageable pageable) {
        return districtRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public  District addDistrict(DistrectRequest distrectRequest) {
        District district=new District();
        mapReqToDistrect(distrectRequest,district);
        return districtRepository.save(district);
    }

    @Transactional
    @Override
    public boolean deleteDistrictById(int id) {
        try {
            Optional<District> districtOptional = districtRepository.findById(id);
            if(districtOptional.isPresent())
            {
                districtRepository.deleteById(id);
                return true; // Deletion successful
            }
            else {
                return  false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    @Transactional
    @Override
    public District updateDistrict(int id, DistrectRequest distrectRequest) {
        Optional<District> districtOptional = districtRepository.findById(id);
        if (districtOptional.isPresent()) {
            District existingDistrict = districtOptional.get();
            existingDistrict.setName(distrectRequest.getDistrectName());
            existingDistrict.setStatus(distrectRequest.getStatus());
            // Update other properties as needed
            return districtRepository.save(existingDistrict);
        } else {
            throw new NotFound("District not found with id " + id);
        }
    }


    private void mapReqToDistrect(DistrectRequest distrectRequest,District district){
        district.setName(distrectRequest.getDistrectName());
        district.setStatus(false);
    }
}
