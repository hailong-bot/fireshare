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
@TableName("user")
public class User {

    @TableId(value = "userId",type = IdType.ASSIGN_ID)
    private Long userId;
    private String username;
    private String password;
    private String telephone;
    private String salt;
    @TableField(value = "registerTime")
    private String registerTime;

}
