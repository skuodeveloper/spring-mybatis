package com.nhga.spring.mybatis.hander;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if ("男".equals(parameter)) {
            ps.setInt(i, 1);
        } else if ("女".endsWith(parameter)) {
            ps.setInt(i, 0);
        } else {
            ps.setInt(i, -1);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int sex = rs.getInt(columnName);
        if (sex == 1) {
            return "男";
        } else if (sex == 0) {
            return "女";
        } else {
            return "无";
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int sex = rs.getInt(columnIndex);
        if (sex == 1) {
            return "男";
        } else if (sex == 0) {
            return "女";
        } else {
            return "无";
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int sex = cs.getInt(columnIndex);
        if (sex == 1) {
            return "男";
        } else if (sex == 0) {
            return "女";
        } else {
            return "无";
        }
    }

}
