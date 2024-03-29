package com.zlw.blog.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Ranger
 * @create 2019-05-31 15:37
 */
public class FastDFSUtils {

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    public static String uploadFile(String FDFS_CLIENT_PAHT, String FDFS_ADDRESS, MultipartFile file) {

        File fdfsConfFile = new File(FDFS_CLIENT_PAHT);
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

        String imgUrl = null;
        try {

            //本地写法
//            ClassPathResource cpr = new ClassPathResource(FDFS_CLIENT_PAHT);
//            ClientGlobal.init(cpr.getClassLoader().getResource(FDFS_CLIENT_PAHT).getPath());

            //服务器写法
            ClientGlobal.init(fdfsConfFile.getPath());

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            NameValuePair nvp[] = new NameValuePair[]{
                    new NameValuePair("item_id", "100010"),
                    new NameValuePair("width", "80"),
                    new NameValuePair("height", "90")
            };
            String fileIds[] = storageClient.upload_file(file.getBytes(), ext, nvp);

            imgUrl = "http://" + FDFS_ADDRESS + "/" + fileIds[0] + "/" + fileIds[1];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }

}
