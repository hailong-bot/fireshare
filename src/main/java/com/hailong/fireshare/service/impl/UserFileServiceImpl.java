package com.hailong.fireshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hailong.fireshare.entity.User;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.mapper.UserFileMapper;
import com.hailong.fireshare.mapper.UserMapper;
import com.hailong.fireshare.service.UserFileService;
import com.hailong.fireshare.service.UserService;
import com.hailong.fireshare.utils.JWTUtil;
import com.hailong.fireshare.vo.UserFileListVo;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFileServiceImpl extends ServiceImpl<UserFileMapper, UserFile> implements UserFileService {

    @Override
    public List<UserFileListVo> getUserFileByFilePath(String filePath, Long userId, Long currentPage, Long pageCount) {
        Long beginCount = (currentPage - 1) * pageCount;
        UserFile userfile = new UserFile();
        userfile.setUserId(userId);
        userfile.setFilePath(filePath);
        List<UserFileListVo> fileList = this.baseMapper.userfileList(userfile, beginCount, pageCount);
        return fileList;
    }
}
