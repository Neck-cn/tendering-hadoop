package cn.ych.tendering.dao;

import cn.ych.tendering.pojo.Report;
import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDao {
    public List<Report> selectAll() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        List<Report> reportList = new ArrayList<>();
        try {
            pre = con.prepareStatement("select e_id,e_name from report where status = 1");
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Report report = new Report();
                report.setE_id(resultSet.getInt("e_id"));
                report.setE_name(resultSet.getString("e_name"));
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return reportList;
    }
}
