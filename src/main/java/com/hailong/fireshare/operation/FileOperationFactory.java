package com.hailong.fireshare.operation;

import com.hailong.fireshare.operation.delete.Deleter;
import com.hailong.fireshare.operation.download.Downloader;
import com.hailong.fireshare.operation.upload.Uploader;

public interface FileOperationFactory {
    Uploader getUploader();
    Downloader getDownloader();
    Deleter getDeleter();
}
