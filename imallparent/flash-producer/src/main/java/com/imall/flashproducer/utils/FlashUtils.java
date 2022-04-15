package com.imall.flashproducer.utils;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 14:46
 */
@Component
@RequiredArgsConstructor
public class FlashUtils{

    private final RedissonClient redissonClient;

    private final RedisTemplate<Object,Object> redisTemplate;

    /**
     * 执行扣库存的脚本
     */
    @Value("${STOCK_LUA}")
    private String stockLua;

    @Value("${ROLL_BACK_LUA}")
    private String rollBackLua;

    @Value("${FLASH_END}")
    private String flashEnd;

    private final String lockKey="redissonLock";

    /**
     * 扣库存
     *
     * @param key 库存key
     * @return 扣减之前剩余的库存【0:库存不足; -1:库存未初始化; 大于0:扣减库存之前的剩余库存】
     */
    public Long stock(String key) {
        return getResult(key, stockLua);
    }

    public Long rollBack(String key){
        return getResult(key, rollBackLua);
    }

    public Long setEnd(String key){
        return getResult(key,flashEnd);
    }
    private Long getResult(String key, String stockLua) {
        Long result=0L;
        RLock lock=redissonClient.getLock(lockKey);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(stockLua,Long.class);
        try {
            boolean res=lock.tryLock(2,8, TimeUnit.SECONDS);
            if(res) {
                result = redisTemplate.execute(redisScript, Collections.singletonList(key));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return result;
    }


}
