package com.hailong.fireshare.operation.download.product;

import com.hailong.fireshare.operation.download.domain.DownloadFile;
import com.hailong.fireshare.operation.download.Downloader;
import com.hailong.fireshare.utils.PathUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class LocalStorageDownLoader extends Downloader {
    @Override
    public void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile) {
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        File file = new File(PathUtil.getStaticPath() + downloadFile.getFileUrl());
        if(file.exists()){
            FileInputStream fis = null;
            try{
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = httpServletResponse.getOutputStream();
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer,0,i);
                    i = bis.read(buffer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(bis != null){
                    try {
                        bis.close();
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
