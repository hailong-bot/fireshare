package com.hailong.fireshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hailong.fireshare.entity.File;
import com.hailong.fireshare.mapper.FileMapper;
import com.hailong.fireshare.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
