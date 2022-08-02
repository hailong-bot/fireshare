package com.hailong.fireshare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="登录VO")
public class LoginVO {
    @Schema(description="用户名")
    private String username;
    @Schema(description="token")
    private String token;
}
