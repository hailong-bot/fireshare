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
@TableName("userFile")
public class UserFile {
    @TableId(value = "userFileId", type = IdType.ASSIGN_ID)
    private Long userFileId;
    private Long userId;
    private Long fileId;
    private String fileName;
    private String filePath;
    private String extendName;
    private Integer isDir;
    private String uploadTime;
}
