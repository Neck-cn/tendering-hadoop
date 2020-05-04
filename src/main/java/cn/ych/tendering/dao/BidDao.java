package cn.ych.tendering.dao;

import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidDao {
    public List<Bid> selectAll() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        List<Bid> bidList = new ArrayList<>();
        try {
            pre = con.prepareStatement("select e_id,e_name from bid");
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setE_name(resultSet.getString("e_name"));
                bid.setE_id(resultSet.getInt("e_id"));
                bidList.add(bid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return bidList;
    }
}
