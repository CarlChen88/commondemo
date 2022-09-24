package com.cx.threadpool;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池参数配置
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {

    public static final String SENTIMENT_EVENT_EXECUTOR = "test_executor";

    @Bean(SENTIMENT_EVENT_EXECUTOR)
    @ConditionalOnMissingBean(ThreadPoolTaskExecutor.class)
    public ThreadPoolTaskExecutor setThreadPool() {

        /**
         * 线程池执行过程 ThreadPoolExecutor
         * 1.当线程数大于核心线程数时，创建线程
         * 2.当线程数大于等于核心线程数时，且任务队列未满时，将任务放入任务队列
         * 3.当线程数大于等于核心线程数，且任务队列已满时，
         *  3.1 若线程数小于最大线程数，创建线程
         *  3.2 若线程数等于最大线程数，采用拒绝策略，默认丢弃任务，抛出异常！
         */
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /**
         * 设置核心线程数：corePoolSize
         * 1.1核心线程会一直存活，即使没有任务执行
         * 1.2当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
         * 1.3设置allowCoreThreadTimeout=true(默认false)时，核心线程会超时关闭
         */
        threadPoolTaskExecutor.setCorePoolSize(1);
        /**
         * 设置最大线程数：maxPoolSize
         * 1.线程池中所允许的最大线程数
         * 2.maxPoolSize>当前线程数>=corePoolSize时,且任务队列已满时，线程池会创建新线程来处理任务.
         * 3.当前线程数=maxPoolSize，且任务队列已满时，线程池会根据rejectedExecutionHandler策略处理
         * 默认是AbortPolicy,丢弃任务，运行时异常!
         */
        threadPoolTaskExecutor.setMaxPoolSize(1);
        /**
         * 阻塞队列：queueCapacity
         * 当核心线程数最大时，新任务会放在队列中排队等待执行!
         */
        threadPoolTaskExecutor.setQueueCapacity(3);
        /**
         * 线程池名称
         */
        threadPoolTaskExecutor.setThreadGroupName("thread-group-name");
        /**
         *  拒绝策略处理rejectedExecutionHandler: 自定义实现RejectedExecutionHandler接口
         *  ThreadPoolExecutor类已有的拒绝测试处理器
         *  1.AbortPolicy 丢弃任务 运行时抛异常
         *  2.CallerRunsPolicy执行任务
         *  3.DiscardPolicy丢弃忽视什么都不会发生
         *  4.DiscardOldestPolicy从队列中踢出最先进入队列(最后一个执行)的任务
         */
        threadPoolTaskExecutor.setRejectedExecutionHandler((new ThreadPoolExecutor.AbortPolicy()));
        //threadPoolTaskExecutor.setRejectedExecutionHandler((new RejectedExecutionHandlerStrategy()));
        return threadPoolTaskExecutor;
    }
}
