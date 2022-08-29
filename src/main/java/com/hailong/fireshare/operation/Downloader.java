package com.hailong.fireshare.operation;

import com.hailong.fireshare.entity.DownloadFile;

import javax.servlet.http.HttpServletResponse;

public abstract class Downloader {
    public abstract void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile);
}
