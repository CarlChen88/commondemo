package com.cx.demo1;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class NormalEnumHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> enumType;
    private E[] enums;

    public NormalEnumHandler(Class<E> enumType) {
        if (enumType == null) {
            throw new IllegalArgumentException("type arguments can not be null");
        }
        this.enumType = enumType;
        this.enums = enumType.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(enumType.getSimpleName() + "does not represent an enum type.");
        }
    }

    public NormalEnumHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        if(jdbcType == null) {
            preparedStatement.setInt(i, (Integer) e.getCode());
        }else {
            preparedStatement.setObject(i, e.getCode(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.wasNull() ? null : convert(resultSet.getInt(s));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.wasNull() ? null : convert(resultSet.getInt(i));
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.wasNull() ? null : convert(callableStatement.getInt(i));
    }

    private E convert(Integer code){
        return Arrays.stream(enums).filter(i->i.getCode() == code)
                .findAny().orElseThrow(()-> new IllegalArgumentException("未知的枚举类型" + code + "请核对" + enumType.getSimpleName()));
    }
}
