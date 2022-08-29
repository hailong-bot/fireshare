package com.hailong.fireshare.operation;

public interface FileOperationFactory {
    Uploader getUploader();
    Downloader getDownloader();
    Deleter getDeleter();
}
