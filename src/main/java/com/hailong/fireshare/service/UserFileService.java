package com.hailong.fireshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hailong.fireshare.entity.User;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.vo.UserFileListVo;

import java.util.List;
import java.util.Map;

public interface UserFileService extends IService<UserFile> {
    List<UserFileListVo> getUserFileByFilePath(String filePath, Long userId, Long currentPage, Long pageCount);

    Map<String, Object> getUserFileByType(int fileType, Long currentPage, Long pageCount, Long userId);
}
