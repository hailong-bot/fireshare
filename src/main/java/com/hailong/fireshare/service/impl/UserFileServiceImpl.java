package com.hailong.fireshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.mapper.UserFileMapper;
import com.hailong.fireshare.mapper.UserMapper;
import com.hailong.fireshare.service.UserFileService;
import com.hailong.fireshare.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserFileServiceImpl extends ServiceImpl<UserFileMapper, UserFile> implements UserFileService {
}
