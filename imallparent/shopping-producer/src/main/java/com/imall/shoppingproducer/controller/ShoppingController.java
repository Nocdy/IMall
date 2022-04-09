package com.imall.shoppingproducer.controller;

import com.alibaba.fastjson.JSONObject;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import com.imall.entities.mall.Goods;
import com.imall.shoppingproducer.service.GoodsService;
import com.imall.shoppingproducer.service.PurchaseService;
import com.imall.shoppingproducer.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 19:59
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ShoppingController {

    private final GoodsService goodsService;

    private final ShoppingListService shoppingListService;

    private final PurchaseService purchaseService;



    @GetMapping("/getList/{listNum}")
    public Result<Object> getList(@PathVariable("listNum") int listNum){
        JSONObject data=new JSONObject();
        data.put("newPage",goodsService.getNewList(listNum));
        return new Result<>(data, StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage());

    }

    @PostMapping("/updateGoods")
    public Result<Object> updateGoods(Goods goods){
        try {
            goodsService.submitUpdate(goods);
            return new Result<>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result<>(StatusCode.ERROR.getCode(),StatusCode.ERROR.getMessage());
        }
    }

    @GetMapping("/getOne/{cid}/{gid}")
    public Result<Object> getOne(@PathVariable("cid")int cid,
                                 @PathVariable("gid") int gid){
        JSONObject data=new JSONObject();
        data.put("goods",goodsService.getOneAndSaveToRedis(gid,cid));
        return new Result<>(data,
                StatusCode.SUCCESS.getCode(),
                StatusCode.SUCCESS.getMessage());
    }



    @PostMapping("/addList/{cid}/{gid}")
    public Result<Object> addList(@PathVariable("cid")int cid,
                                  @PathVariable("gid") int gid){
        Goods addGoods=goodsService.getById(gid);
        shoppingListService.addList(cid,addGoods);
        return new Result<>(StatusCode.SUCCESS.getCode(),
                StatusCode.SUCCESS.getMessage());
    }

    @GetMapping("/purchase/{cid}")
    public Result<Object> purchase(@PathVariable("cid") int cid){
        purchaseService.purchase(cid);
        return new Result<>(StatusCode.SUCCESS.getCode(),
                StatusCode.SUCCESS.getMessage());
    }


}
