package com.cx.async;

import com.cx.threadpool.ThreadPoolTaskExecutorConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * 异步处理方式
 * 1.加@Async注解和相应的线程池
 */
@Service
public class AsyncService {

    @Async(ThreadPoolTaskExecutorConfig.SENTIMENT_EVENT_EXECUTOR)
    public void testAsync() throws InterruptedException {
        System.out.println("async service");
    }
}
