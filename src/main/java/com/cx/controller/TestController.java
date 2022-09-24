package com.cx.controller;

import com.cx.aspect.model01.TargetDatasource;
import com.cx.dao.TestDao;
import com.cx.data.TargetDataSource;
import com.cx.exception.MyException;
import com.cx.service.extend.AService;
import com.cx.service.extend.DService;
import com.cx.util.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class TestController {

    @Autowired
    private TestDao testDao;
    @Autowired
    private List<AService> aServiceList;
    @Autowired
    private DService dService;

    @Autowired
    private TestService testService;

    private String Astr;

    public String getAstr() {
        return Astr;
    }

    public void setAstr(String astr) {
        Astr = astr;
    }

    @GetMapping(value = "/test")
    @TargetDataSource(value = "test")
    public Map<String,Object> getList() throws Exception {
        System.out.println(getAstr());
        aServiceList.stream().forEach(AService::test1);
        dService.test4();
        Map<String,Object> result = new HashMap<>();
        result.put("data",testDao.getList());
        //if(true) throw new Exception("异常");
        //if(true) throw new MyException("自定义异常");
        return result;
    }
}
