package com.imall.shoppingproducer.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/2 19:13
 */
public interface UploadFileService {


    /**
     * 上传图片服务
     * @param file 上传文件
     * @param goodsId 商品id
     * @param vendorId 商家id
     * @return 返回图片url
     * @throws FileNotFoundException 文件异常
     */
    String uploadGoodsImage(MultipartFile file,Integer goodsId,Integer vendorId) throws FileNotFoundException;
}
