package com.hailong.fireshare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="注册DTO",required = true)
public class CreateFileDTO {
    @Schema(description="文件名")
    private String fileName;
    @Schema(description="文件路径")
    private String filePath;
}
