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
@TableName("userFile")
public class UserFile {
    @TableId(value = "userFileId", type = IdType.ASSIGN_ID)
    private Long userFileId;
    @TableField("userId")
    private Long userId;
    @TableField("fileId")
    private Long fileId;
    @TableField("fileName")
    private String fileName;
    @TableField("filePath")
    private String filePath;
    @TableField("extendName")
    private String extendName;
    @TableField("isDir")
    private Integer isDir;
    @TableField("uploadTime")
    private String uploadTime;
    // 删除标志 0-未删除 1-已删除
    @TableField("deleteFlag")
    private Integer deleteFlag;
    // 删除时间
    @TableField("deleteTime")
    private String deleteTime;
    //删除批次号
    @TableField("deleteBatchNum")
    private String deleteBatchNum;
}
