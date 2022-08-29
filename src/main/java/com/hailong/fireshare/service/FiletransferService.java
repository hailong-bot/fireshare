package com.hailong.fireshare.service;

import com.hailong.fireshare.dto.DownloadFileDTO;
import com.hailong.fireshare.dto.UploadFileDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FiletransferService {
    void uploadFile(HttpServletRequest request, UploadFileDto uploadFileDto, Long userId);
    void downloadFile(HttpServletResponse httpServletResponse, DownloadFileDTO downloadFileDTO);
}
