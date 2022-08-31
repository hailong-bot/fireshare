package com.hailong.fireshare.operation.delete;

import com.hailong.fireshare.operation.delete.domain.DeleteFile;

public abstract class Deleter {
    public abstract void delete(DeleteFile deleteFile);
}
