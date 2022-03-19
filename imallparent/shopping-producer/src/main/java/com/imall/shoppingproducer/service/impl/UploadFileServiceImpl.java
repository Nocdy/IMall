package com.imall.shoppingproducer.service.impl;

import com.imall.shoppingproducer.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/2 19:16
 */
@Service
@Slf4j
public class UploadFileServiceImpl implements UploadFileService {



    @Value(value = "${baseUrl}")
    private String baseUrl;

    @Override
    public String uploadGoodsImage(MultipartFile file, Integer goodsId, Integer vendorId) throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        String dir = path + "/static/goods-image/" + vendorId.toString();
        boolean status=true;
        File uploadDir = new File(dir);
        if (!uploadDir.exists()){
            status=uploadDir.mkdir();
        }
        if (file != null&&status) {
            String oldName = file.getOriginalFilename();
            String suffix = oldName != null ? oldName.substring(oldName.lastIndexOf(".")) : null;
            String newName = goodsId.toString() + suffix;
            log.info("用户上传图片名称:{}", oldName);
            File image = new File(dir + "/", newName);
            try {
                log.info("将 {} 存储到 {} 中", newName, dir);
                file.transferTo(image);
                return baseUrl +"/goods-image/"+vendorId+"/"+ newName;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
