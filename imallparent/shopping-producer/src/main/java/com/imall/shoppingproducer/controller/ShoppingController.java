package com.imall.shoppingproducer.controller;

import com.alibaba.fastjson.JSONObject;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import com.imall.entities.mall.Goods;
import com.imall.shoppingproducer.service.GoodsService;
import com.imall.shoppingproducer.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLDataException;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 19:59
 */
@RestController
@RequiredArgsConstructor
public class ShoppingController {

    private final GoodsService goodsService;

    private final UploadFileService uploadFileService;

    @GetMapping("/getList/{listNum}")
    public Result<Object> getList(@PathVariable("listNum") int listNum){
        JSONObject data=new JSONObject();
        data.put("newPage",goodsService.getNewList(listNum));
        return new Result<>(data, StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage());

    }

    @PostMapping("/updateGoods")
    @Transactional(rollbackFor = {Exception.class})
    public Result<Object> updateGoods(Goods goods) throws FileNotFoundException, SQLDataException {
        goodsService.getBaseMapper().insert(goods);
        String url=uploadFileService.uploadGoodsImage(goods.getFile(),goods.getId(),goods.getVendorId());
        if(url==null){
            throw new SQLDataException();
        }
        goods.setImagePath(url);
        goodsService.updateById(goods);
        return new Result<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMessage());
    }


}
