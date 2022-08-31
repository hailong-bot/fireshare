package com.hailong.fireshare.operation.download;

import com.hailong.fireshare.operation.download.domain.DownloadFile;

import javax.servlet.http.HttpServletResponse;

public abstract class Downloader {
    public abstract void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile);
}
