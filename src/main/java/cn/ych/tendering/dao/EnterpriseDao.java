package cn.ych.tendering.dao;

import cn.ych.tendering.pojo.Enterprise;
import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseDao {

    public List<Enterprise> selectAll() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        List<Enterprise> enterpriseList = new ArrayList<>();
        try {
            pre = con.prepareStatement("select * from enterprise");
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Enterprise enterprise = new Enterprise();
                enterprise.setName(resultSet.getString("name"));
                enterprise.setId(resultSet.getInt("id"));
                enterpriseList.add(enterprise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return enterpriseList;
    }
}
