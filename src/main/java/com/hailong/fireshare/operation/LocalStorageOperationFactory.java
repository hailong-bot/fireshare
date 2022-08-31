package com.hailong.fireshare.operation;

import com.hailong.fireshare.operation.delete.Deleter;
import com.hailong.fireshare.operation.download.Downloader;
import com.hailong.fireshare.operation.FileOperationFactory;
import com.hailong.fireshare.operation.upload.Uploader;
import com.hailong.fireshare.operation.delete.product.LocalStorageDeleter;
import com.hailong.fireshare.operation.download.product.LocalStorageDownLoader;
import com.hailong.fireshare.operation.upload.product.LocalStorageUploader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LocalStorageOperationFactory implements FileOperationFactory {
    @Qualifier
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
