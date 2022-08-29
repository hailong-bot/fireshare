package com.hailong.fireshare.product;

import com.hailong.fireshare.operation.Deleter;
import com.hailong.fireshare.operation.Downloader;
import com.hailong.fireshare.operation.FileOperationFactory;
import com.hailong.fireshare.operation.Uploader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LocalStorageOperationFactory implements FileOperationFactory {
    @Resource
    LocalStorageUploader localStorageUploader;
    @Resource
    LocalStorageDownLoader localStorageDownloader;
    @Resource
    LocalStorageDeleter localStorageDeleter;

    @Override
    public Uploader getUploader() {
        return this.localStorageUploader;
    }

    @Override
    public Downloader getDownloader() {
        return this.localStorageDownloader;
    }

    @Override
    public Deleter getDeleter() {
        return this.localStorageDeleter;
    }
}
