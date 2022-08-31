package com.hailong.fireshare.operation.delete.product;

import com.hailong.fireshare.operation.delete.domain.DeleteFile;
import com.hailong.fireshare.operation.delete.Deleter;
import com.hailong.fireshare.utils.FileUtil;
import com.hailong.fireshare.utils.PathUtil;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LocalStorageDeleter extends Deleter {
    @Override
    public void delete(DeleteFile deleteFile) {
        File file = new File(PathUtil.getStaticPath() + deleteFile.getFileUrl());
        if(file.exists()){
            file.delete();
        }

        if(FileUtil.isImageFile(FileUtil.getFileExtendName(deleteFile.getFileUrl()))){
            File minFile = new File(PathUtil.getStaticPath() + deleteFile.getFileUrl().replace(deleteFile.getTimeStampName(), deleteFile.getTimeStampName() + "_min"));
            if(minFile.exists()){
                minFile.delete();
            }
        }
    }
}
