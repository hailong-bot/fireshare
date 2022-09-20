package com.hailong.fireshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField(value = "timeStampName")
    private String timeStampName;
    @TableField(value = "fileUrl")
    private String fileUrl;
    @TableField(value = "fileSize")
    private Long fileSize;
    // 存储类型 0-本地存储 1-阿里云存储, 2-FastDFS存储
    private Integer storageType;
    //md5唯一标识
    private String identifier;
    //引用数量
    @TableField(value = "pointCount")
    private Integer pointCount;
}
