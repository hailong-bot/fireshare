package com.hailong.fireshare.service.impl;

import com.hailong.fireshare.dto.DownloadFileDTO;
import com.hailong.fireshare.dto.UploadFileDto;
import com.hailong.fireshare.entity.DownloadFile;
import com.hailong.fireshare.entity.File;
import com.hailong.fireshare.entity.UploadFile;
import com.hailong.fireshare.entity.UserFile;
import com.hailong.fireshare.mapper.FileMapper;
import com.hailong.fireshare.mapper.UserFileMapper;
import com.hailong.fireshare.operation.Downloader;
import com.hailong.fireshare.operation.FileOperationFactory;
import com.hailong.fireshare.operation.Uploader;
import com.hailong.fireshare.service.FiletransferService;
import com.hailong.fireshare.utils.DateUtil;
import com.hailong.fireshare.utils.PropertiesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class FiletransferServiceImpl implements FiletransferService {
    @Resource
    FileMapper fileMapper;
    @Resource
    UserFileMapper userfileMapper;

    @Resource
    FileOperationFactory localStorageOperationFactory;

    @Override
    public void uploadFile(HttpServletRequest request, UploadFileDto uploadFileDto, Long userId) {

        Uploader uploader = null;
        UploadFile uploadFile = new UploadFile();
        uploadFile.setChunkNumber(uploadFileDto.getChunkNumber());
        uploadFile.setChunkSize(uploadFileDto.getChunkSize());
        uploadFile.setTotalChunks(uploadFileDto.getTotalChunks());
        uploadFile.setIdentifier(uploadFileDto.getIdentifier());
        uploadFile.setTotalSize(uploadFileDto.getTotalSize());
        uploadFile.setCurrentChunkSize(uploadFileDto.getCurrentChunkSize());
        String storageType = PropertiesUtil.getProperty("file.storage-type");
        synchronized (FiletransferService.class) {
            if ("0".equals(storageType)) {
                uploader = localStorageOperationFactory.getUploader();
            }
        }

        List<UploadFile> uploadFileList = uploader.upload(request, uploadFile);
        for (int i = 0; i < uploadFileList.size(); i++){
            uploadFile = uploadFileList.get(i);
            File file = new File();

            file.setIdentifier(uploadFileDto.getIdentifier());
            file.setStorageType(Integer.parseInt(storageType));
            file.setTimeStampName(uploadFile.getTimeStampName());
            if (uploadFile.getSuccess() == 1){
                file.setFileUrl(uploadFile.getUrl());
                file.setFileSize(uploadFile.getFileSize());
                file.setPointCount(1);
                fileMapper.insert(file);
                UserFile userFile = new UserFile();
                userFile.setFileId(file.getFileId());
                userFile.setExtendName(uploadFile.getFileType());
                userFile.setFileName(uploadFile.getFileName());
                userFile.setFilePath(uploadFileDto.getFilePath());
                //userFile.setDeleteFlag(0);
                userFile.setUserId(userId);
                userFile.setIsDir(0);
                userFile.setUploadTime(DateUtil.getCurrentTime());
                userfileMapper.insert(userFile);
            }

        }
    }

    @Override
    public void downloadFile(HttpServletResponse httpServletResponse, DownloadFileDTO downloadFileDTO) {
        UserFile userFile = userfileMapper.selectById(downloadFileDTO.getUserFileId());

        String fileName = userFile.getFileName() + "." + userFile.getExtendName();
        try {
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpServletResponse.setContentType("application/force-download");// 设置强制下载不打开
        httpServletResponse.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名


        File file = fileMapper.selectById(userFile.getFileId());
        Downloader downloader = null;
        if (file.getStorageType() == 0) {
            downloader = localStorageOperationFactory.getDownloader();
        }
        DownloadFile uploadFile = new DownloadFile();
        uploadFile.setFileUrl(file.getFileUrl());
        uploadFile.setTimeStampName(file.getTimeStampName());
        downloader.download(httpServletResponse, uploadFile);
    }
}
