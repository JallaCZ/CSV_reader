package org.example.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@ToString
@Getter
@Setter
public class Film {
    private String name;
    private BigDecimal rating ;

public void ssName(String s){
    this.name=s;
}
}
