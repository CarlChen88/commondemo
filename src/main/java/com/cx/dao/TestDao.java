package com.cx.dao;

import com.cx.demo1.UserVO;
import com.cx.model.EnumTestVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestDao {
    List<Map<String,Object>> getList();
    EnumTestVO getEnumVO();

    void saveEnumVO(EnumTestVO testVO);

    UserVO getUser();
}
