package cn.ych.tendering.dao;

import cn.ych.tendering.pojo.Tendering;
import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TenderingDao {

    public List<Tendering> selectAll() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        List<Tendering> tenderingList = new ArrayList<>();
        try {
            pre = con.prepareStatement("select bid.e_id,bid.e_name from tendering,bid where win_id != 0 and bid.id = win_id");
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Tendering tendering = new Tendering();
                tendering.setWin_id(resultSet.getInt("bid.e_id"));
                tendering.setName(resultSet.getString("bid.e_name"));
                tenderingList.add(tendering);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return tenderingList;
    }

    public List<Tendering> selectAllTendering() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        List<Tendering> tenderingList = new ArrayList<>();
        try {
            pre = con.prepareStatement("select e_id,name,end_time,win_id from tendering");
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Tendering tendering = new Tendering();
                tendering.setWin_id(resultSet.getInt("win_id"));
                tendering.setName(resultSet.getString("name"));
                tendering.setE_id(resultSet.getInt("e_id"));
                tendering.setEnd_time(resultSet.getString("end_time"));
                tenderingList.add(tendering);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return tenderingList;
    }
}
