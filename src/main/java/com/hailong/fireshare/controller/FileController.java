package com.hailong.fireshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hailong.fireshare.dto.CreateFileDTO;
import com.hailong.fireshare.dto.UserFileListDto;
import com.hailong.fireshare.entity.RestResult;
import com.hailong.fireshare.entity.User;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.service.FileService;
import com.hailong.fireshare.service.UserFileService;
import com.hailong.fireshare.service.UserService;
import com.hailong.fireshare.utils.DateUtil;
import com.hailong.fireshare.vo.UserFileListVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "file", description = "该接口为文件接口，主要用来做一些文件的基本操作，如创建目录，删除，移动，复制等。")
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;
    @Resource
    UserService userService;
    @Resource
    UserFileService userfileService;

    @Operation(summary = "创建文件", description = "目录(文件夹)的创建", tags = {"file"})
    @PostMapping(value = "/createfile")
    @ResponseBody
    public RestResult<String> createFile(@RequestBody CreateFileDTO createFileDto, @RequestHeader("token") String token){

        User userByToken = userService.getUserByToken(token);
        if(ObjectUtils.isEmpty(userByToken)){
            return RestResult.fail().message("token认证失败");
        }
        LambdaQueryWrapper<UserFile> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserFile::getFileName,"");
        lambdaQueryWrapper.eq(UserFile::getFilePath, "");
        lambdaQueryWrapper.eq(UserFile::getUserId, 0);
        List<UserFile> list = userfileService.list(lambdaQueryWrapper);
        if(!ObjectUtils.isEmpty(list)){
            return RestResult.fail().message("同目录下文件名重复");
        }
        UserFile userFile = new UserFile();
        userFile.setUserId(userByToken.getUserId());
        userFile.setFileName(createFileDto.getFileName());
        userFile.setFilePath(createFileDto.getFilePath());
        userFile.setIsDir(1);
        userFile.setUploadTime(DateUtil.getCurrentTime());
        userfileService.save(userFile);
        return RestResult.success();
    }

    @Operation(summary = "获取文件列表", description = "用来做前台文件列表展示", tags = { "file" })
    @GetMapping(value = "/getfilelist")
    @ResponseBody
    public RestResult<UserFileListVo> getUserfileList(UserFileListDto userfileListDto,
                                                      @RequestHeader("token") String token) {


        User sessionUser = userService.getUserByToken(token);
        if (sessionUser == null) {
            return RestResult.fail().message("token验证失败");

        }
        List<UserFileListVo> fileList = userfileService.getUserFileByFilePath(userfileListDto.getFilePath(),
                sessionUser.getUserId(), userfileListDto.getCurrentPage(), userfileListDto.getPageCount());

        LambdaQueryWrapper<UserFile> userFileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userFileLambdaQueryWrapper.eq(UserFile::getUserId, sessionUser.getUserId()).eq(UserFile::getFilePath, userfileListDto.getFilePath());
        long total = userfileService.count(userFileLambdaQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", fileList);
        return RestResult.success().data(map);

    }
}
