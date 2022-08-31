package com.hailong.fireshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hailong.fireshare.entity.RecoveryFile;
import com.hailong.fireshare.vo.RecoveryFileListVO;

import java.util.List;

public interface RecoveryFileMapper extends BaseMapper<RecoveryFile> {
    List<RecoveryFileListVO> selectRecoveryFileList();
}
