package com.hailong.fireshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file")
public class File {
    @TableId(value = "fileId", type = IdType.ASSIGN_ID)
    private Long fileId;
    private String timeStampName;
    private String fileUrl;
    private Long fileSize;
}
