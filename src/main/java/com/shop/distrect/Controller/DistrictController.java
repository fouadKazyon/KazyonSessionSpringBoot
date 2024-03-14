package com.shop.distrect.Controller;

import com.shop.Shop.Model.Response;
import com.shop.distrect.Model.DistrectRequest;
import com.shop.distrect.Model.District;
import com.shop.distrect.Service.DistrectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/district")
public class DistrictController {

    @Autowired
    DistrectService distrectService;

    @PostMapping
    public ResponseEntity<Response> createDistrect(
            @Valid
            @RequestBody DistrectRequest distrectRequest) {
     District district= distrectService.addDistrict(distrectRequest);

        Response response = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("Distrect added successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .data(district)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping
    public ResponseEntity<Response> getAllDistrect(Pageable pageable) {
        Page<District> districts = distrectService.getAllDistricts(pageable);

        Response response = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("All Distrects retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(districts)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<Response> getDistrectById(@PathVariable Integer districtId) {
        Optional<District> district = distrectService.getDistrictById(districtId);

            Response response = Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Shops retrieved successfully")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .data(district)
                    .build();
            return ResponseEntity.ok(response);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteShopById(@PathVariable int id) {
            boolean isDeleted = distrectService.deleteDistrictById(id);
            if (isDeleted) {

                Response response = Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("District Deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(null)
                        .build();
                return ResponseEntity.ok(response);
            } else {
                Response response = Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("District not found")
                        .status(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
    }


    @PutMapping("/{districtId}")
    public ResponseEntity<Response> updateDistrict(@PathVariable Integer districtId, @RequestBody DistrectRequest distrectRequest) {
        try {
            District updatedDistrict = distrectService.updateDistrict(districtId, distrectRequest);
            Response response = Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("District updated successfully")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .data(updatedDistrict)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response response = Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("District Id not found")
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
