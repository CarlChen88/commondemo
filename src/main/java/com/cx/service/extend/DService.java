package com.cx.service.extend;

import org.springframework.stereotype.Service;

@Service
public class DService extends CService {
    @Override
    public void test1() {
        System.out.println("test11111");
    }
}
