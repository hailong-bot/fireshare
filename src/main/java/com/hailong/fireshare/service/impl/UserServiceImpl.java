package com.hailong.fireshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hailong.fireshare.common.RestResult;
import com.hailong.fireshare.entity.User;
import com.hailong.fireshare.mapper.UserMapper;
import com.hailong.fireshare.service.UserService;
import com.hailong.fireshare.utils.DateUtil;
import com.hailong.fireshare.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {

    @Resource
    UserMapper userMapper;

    @Resource
    JWTUtil jwtUtil;

    @Override
    public RestResult<String> registerUser(User user) {
        String telephone = user.getTelephone();
        String password = user.getPassword();
        if (!StringUtils.hasLength(telephone) || !StringUtils.hasLength(password)){
            return RestResult.fail().message("手机号或密码不能为空！");
        }
        if (isTelePhoneExit(telephone)){
            return RestResult.fail().message("手机号已存在！");
        }
        String salt = UUID.randomUUID().toString().replace("-","").substring(15);
        String passwordAndSalt = password + salt;
        String newPassword = DigestUtils.md5DigestAsHex(passwordAndSalt.getBytes());
        user.setSalt(salt);
        user.setPassword(newPassword);
        user.setRegisterTime(DateUtil.getCurrentTime());
        int insert = userMapper.insert(user);
        if(insert == 1){
            return RestResult.success();
        }
        else {
            return RestResult.fail().message("注册用户失败,请检查输入信息");
        }
    }

    @Override
    public RestResult<User> login(User user) {
        String telephone = user.getTelephone();
        String password = user.getPassword();

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getTelephone, telephone);
        User saveUser = userMapper.selectOne(lambdaQueryWrapper);
        String salt = saveUser.getSalt();
        String passwordAndSalt = password + salt;
        String newPassword = DigestUtils.md5DigestAsHex(passwordAndSalt.getBytes());
        if (newPassword.equals(saveUser.getPassword())) {
            saveUser.setPassword("");
            saveUser.setSalt("");
            return RestResult.success().data(saveUser);
        } else {
            return RestResult.fail().message("手机号或密码错误！");
        }
    }

    private boolean isTelePhoneExit(String telephone){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getTelephone, telephone);
        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        if(list != null && !list.isEmpty()){
            return true;
        }
        return false;
    }



    @Override
    public User getUserByToken(String token) {
        User tokenUserInfo = null;
        try {
            Claims c = jwtUtil.parseJWT(token);
            String subject = c.getSubject();
            ObjectMapper objectMapper = new ObjectMapper();
            tokenUserInfo = objectMapper.readValue(subject, User.class);
        } catch (Exception e) {
            log.error("解码异常");
            return null;

        }
        return tokenUserInfo;
    }
}
