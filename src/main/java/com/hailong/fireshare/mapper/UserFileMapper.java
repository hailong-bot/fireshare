package com.hailong.fireshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.vo.UserFileListVo;

import java.util.List;

public interface UserFileMapper extends BaseMapper<UserFile> {
    List<UserFileListVo> userfileList(UserFile userfile, Long beginCount, Long pageCount);
}
