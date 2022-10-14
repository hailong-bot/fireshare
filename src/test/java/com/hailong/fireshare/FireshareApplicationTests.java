package com.hailong.fireshare;

import com.hailong.fireshare.entity.User;
import com.hailong.fireshare.service.UserService;
import com.hailong.fireshare.utils.JWTUtil;
import com.hailong.fireshare.utils.PathUtil;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class FireshareApplicationTests {

    @Resource
    UserService userService;

    @Resource
    JWTUtil jwtUtil;

    @Test
    void contextLoads() {
        String projectRootPath = PathUtil.getProjectRootPath();
        System.out.println("projectRootPath = " + projectRootPath);
        Assert.notNull(null, () -> {
            return "name";
        });
    }

}
