package com.imall.clientconsumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.imall.clientconsumer.handler.MyBlockHandler;
import com.imall.clientconsumer.service.ConfirmService;
import com.imall.clientconsumer.service.FlashService;
import com.imall.clientconsumer.service.ShoppingService;
import com.imall.dto.OrderFlag;
import com.imall.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/27 20:26
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class ShoppingController {

    private final ConfirmService confirmService;

    private final FlashService fLashService;

    private final ShoppingService shoppingService;

    @GetMapping("/getList/{listNum}")
    public Result<Object> getList(@PathVariable("listNum") int listNum){
        return shoppingService.getList(listNum);
    }

    @PostMapping("/confirmOrder")
    Result<Object> confirmOrder(@RequestBody OrderFlag orderFlag){
        return confirmService.confirmOrder(orderFlag);
    }

    @RequestMapping("/Flash/{cid}/{gid}")
    @SentinelResource(value = "Flash",blockHandlerClass = MyBlockHandler.class,blockHandler = "flashHandler")
    Result<Object> flash(@PathVariable("cid") Integer cid,
                         @PathVariable("gid") Integer gid){
        return fLashService.flash(cid,gid);
    }

    @GetMapping("/getOne/{cid}/{gid}")
    Result<Object> getOne(@PathVariable("cid") int cid,
                          @PathVariable("gid") int gid){
        return shoppingService.getOne(cid,gid);
    }

    @GetMapping("/addList/{cid}/{gid}")
    Result<Object> addList(@PathVariable("cid") int cid,
                           @PathVariable("gid") int gid){
        return shoppingService.addList(cid,gid);
    }

    @GetMapping("/showList/{cid}")
    public Result<Object> showList(@PathVariable("cid") int cid){
        return  shoppingService.showList(cid);
    }

    @GetMapping("/purchase/{cid}")
    Result<Object> purchase(@PathVariable("cid") int cid){
        return shoppingService.purchase(cid);
    }

    @PostMapping("/showOrder")
    Result<Object> showOrder(@RequestBody OrderFlag orderFlag){
        return  confirmService.showOrder(orderFlag);
    }

    @GetMapping("/search/{keyword}")
    public Result<Object> searchGoods(@PathVariable("keyword") String keyword){
        return shoppingService.searchGoods(keyword);
    }

    @GetMapping("/carouselGet")
    public Result<Object> carouselGet(){
        return shoppingService.carouselGet();
    }

}
