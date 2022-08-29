package com.hailong.fireshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hailong.fireshare.constant.FileConstant;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.mapper.UserFileMapper;
import com.hailong.fireshare.service.UserFileService;
import com.hailong.fireshare.vo.UserFileListVo;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<String, Object> getUserFileByType(int fileType, Long currentPage, Long pageCount, Long userId) {
        Long beginCount = (currentPage - 1) * pageCount;
        List<UserFileListVo> fileList;
        Long total;
        if (fileType == FileConstant.OTHER_TYPE) {

            List<String> arrList = new ArrayList<>();
            arrList.addAll(Arrays.asList(FileConstant.DOC_FILE));
            arrList.addAll(Arrays.asList(FileConstant.IMG_FILE));
            arrList.addAll(Arrays.asList(FileConstant.VIDEO_FILE));
            arrList.addAll(Arrays.asList(FileConstant.MUSIC_FILE));

            fileList = this.baseMapper.selectFileNotInExtendNames(arrList,beginCount, pageCount, userId);
            total = this.baseMapper.selectCountNotInExtendNames(arrList,beginCount, pageCount, userId);
        }else {
            List<String> fileExtends = null;
            if (fileType == FileConstant.IMAGE_TYPE) {
                fileExtends = Arrays.asList(FileConstant.IMG_FILE);
            } else if (fileType == FileConstant.DOC_TYPE) {
                fileExtends = Arrays.asList(FileConstant.DOC_FILE);
            } else if (fileType == FileConstant.VIDEO_TYPE) {
                fileExtends = Arrays.asList(FileConstant.VIDEO_FILE);
            } else if (fileType == FileConstant.MUSIC_TYPE) {
                fileExtends = Arrays.asList(FileConstant.MUSIC_FILE);
            }
            fileList = this.baseMapper.selectFileByExtendName(fileExtends, beginCount, pageCount,userId);
            total = this.baseMapper.selectCountByExtendName(fileExtends, beginCount, pageCount,userId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list",fileList);
        map.put("total", total);
        return map;
    }
}
