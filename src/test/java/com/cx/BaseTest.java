package com.cx;

import com.alibaba.fastjson.JSON;
import com.cx.async.AsyncService;
import com.cx.dao.TestDao;
import com.cx.demo1.UserVO;
import com.cx.model.EnumTestVO;
import com.cx.model.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    @Autowired
    private TestDao testDao;

    @Autowired
    private AsyncService asyncService;

    @Test
    public void test1(){
        EnumTestVO testVO = testDao.getEnumVO();
        String name = testVO.getUserType().getName();
        System.out.println("name:"+name);
        System.out.println(JSON.toJSONString(testVO));
    }

    @Test
    public void test2(){
        EnumTestVO testVO = new EnumTestVO();
        testVO.setCode("234");
        testVO.setName("liziwei");
        testVO.setUserType(UserType.TEACHER);
        testDao.saveEnumVO(testVO);
    }

    @Test
    public void test3(){
        UserVO userVO = testDao.getUser();
        System.out.println(userVO);
    }

    @Test
    public void test4() throws InterruptedException {
        for(int i=0;i<3;i++){
            asyncService.testAsync();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
