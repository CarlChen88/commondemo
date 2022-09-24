package com.cx.service.extend;

import org.springframework.stereotype.Service;

@Service
public class EService extends CService {
    @Override
    public void test1() {
        System.out.println("EService.............");
    }
}
