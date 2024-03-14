package com.shop.distrect.Model;
import jakarta.validation.constraints.NotBlank;

 import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrectRequest {

    @NotBlank(message = "dcName can not be empty")
    private String  DistrectName;
    private boolean status;

    public Boolean getStatus() {
        return  status;
    }
}
