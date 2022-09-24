package com.cx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/threadpool")
public class ThreadPoolController {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test () {
        for (int i=0; i<4;i++) {
            Runnable task = new Runnable(){
                @Override
                public void run() {
                    System.out.println("=======");
                }
            };
            threadPoolTaskExecutor.execute(task);
        }
    }
}
