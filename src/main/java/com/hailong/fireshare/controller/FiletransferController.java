package com.hailong.fireshare.controller;

import com.hailong.fireshare.common.RestResult;
import com.hailong.fireshare.dto.DownloadFileDTO;
import com.hailong.fireshare.dto.UploadFileDto;
import com.hailong.fireshare.entity.*;
import com.hailong.fireshare.service.FileService;
import com.hailong.fireshare.service.FiletransferService;
import com.hailong.fireshare.service.UserFileService;
import com.hailong.fireshare.service.UserService;
import com.hailong.fireshare.utils.DateUtil;
import com.hailong.fireshare.utils.FileUtil;
import com.hailong.fireshare.vo.UploadFileVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "filetransfer", description = "该接口为文件传输接口，主要用来做文件的上传和下载")
@RestController
@RequestMapping("/filetransfer")
public class FiletransferController {
    @Resource
    UserService userService;
    @Resource
    FileService fileService;
    @Resource
    UserFileService userfileService;
    @Resource
    FiletransferService filetransferService;

    @Operation(summary = "极速上传", description = "校验文件MD5判断文件是否存在，如果存在直接上传成功并返回skipUpload=true，如果不存在返回skipUpload=false需要再次调用该接口的POST方法", tags = {"filetransfer"})
    @GetMapping(value = "/uploadfile")
    @ResponseBody
    public RestResult<UploadFileVo> uploadFileSpeed(UploadFileDto uploadFileDto, @RequestHeader("token") String token) {

        User sessionUser = userService.getUserByToken(token);
        if (sessionUser == null){

            return RestResult.fail().message("未登录");
        }

        UploadFileVo uploadFileVo = new UploadFileVo();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("identifier", uploadFileDto.getIdentifier());
        synchronized (FiletransferController.class) {
            List<File> list = fileService.listByMap(param);
            if (list != null && !list.isEmpty()) {
                File file = list.get(0);

                UserFile userfile = new UserFile();
                userfile.setFileId(file.getFileId());
                userfile.setUserId(sessionUser.getUserId());
                userfile.setFilePath(uploadFileDto.getFilePath());
                String fileName = uploadFileDto.getFilename();
                userfile.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
                userfile.setExtendName(FileUtil.getFileExtendName(fileName));
                userfile.setIsDir(0);
                userfile.setDeleteFlag(0);
                userfile.setUploadTime(DateUtil.getCurrentTime());
                userfileService.save(userfile);
                // fileService.increaseFilePointCount(file.getFileId());
                uploadFileVo.setSkipUpload(true);

            } else {
                uploadFileVo.setSkipUpload(false);

            }
        }
        return RestResult.success().data(uploadFileVo);
    }
    @Operation(summary = "上传文件", description = "真正的上传文件接口", tags = {"filetransfer"})
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public RestResult<UploadFileVo> uploadFile(HttpServletRequest request, UploadFileDto uploadFileDto, @RequestHeader("token") String token) {

        User sessionUser = userService.getUserByToken(token);
        if (sessionUser == null){
            return RestResult.fail().message("未登录");
        }


        filetransferService.uploadFile(request, uploadFileDto, sessionUser.getUserId());
        UploadFileVo uploadFileVo = new UploadFileVo();
        return RestResult.success().data(uploadFileVo);

    }

    @Operation(summary = "下载文件", description = "下载文件接口", tags = {"filetransfer"})
    @RequestMapping(value = "/downloadfile", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, DownloadFileDTO downloadFileDTO) {
        filetransferService.downloadFile(response, downloadFileDTO);
    }

    @Operation(summary = "获取存储信息", description = "获取存储信息", tags = {"filetransfer"})
    @RequestMapping(value = "/getstorage", method = RequestMethod.GET)
    @ResponseBody
    public RestResult<Long> getStorage(@RequestHeader("token") String token) {

        User sessionUserBean = userService.getUserByToken(token);
        Storage storageBean = new Storage();

        Long storageSize = filetransferService.selectStorageSizeByUserId(sessionUserBean.getUserId());
        return RestResult.success().data(storageSize);

    }

}
