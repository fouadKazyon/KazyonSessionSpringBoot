package com.shop.distrect.Model;


import com.shop.Shop.Model.Shop;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class District {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean status;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Shop> shops;
}
