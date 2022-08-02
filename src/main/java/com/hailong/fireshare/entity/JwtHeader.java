package com.hailong.fireshare.entity;

import lombok.Data;

@Data
public class JwtHeader {
    private String alg;
    private String typ;
}
