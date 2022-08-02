package com.hailong.fireshare.entity;

import lombok.Data;

@Data
public class RegisterdClaims {
    private String iss;
    private String exp;
    private String sub;
    private String aud;
}
