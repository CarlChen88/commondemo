package com.cx.model;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbEnumTypeHandler extends BaseTypeHandler<EnumDb> {

    private Class<EnumDb> type;

    public DbEnumTypeHandler(Class<EnumDb> type){
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i,
                                    EnumDb enumDb, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, enumDb.getCode());
    }

    @Override
    public EnumDb getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        if(resultSet.wasNull()) {
            return null;
        }else {
            return convert(code);
        }
    }

    @Override
    public EnumDb getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        if(resultSet.wasNull()) {
            return null;
        }else {
            return convert(code);
        }
    }

    @Override
    public EnumDb getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        if(callableStatement.wasNull()) {
            return null;
        }else {
            return convert(code);
        }
    }

    private EnumDb convert(int code) {
        EnumDb[] dbEnums = type.getEnumConstants();
        for (EnumDb dbEnum : dbEnums) {
            if(dbEnum.getCode().equals(code)){
                return dbEnum;
            }
        }
        return null;
    }
}
