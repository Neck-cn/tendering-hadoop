package cn.ych.tendering.dao;

import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExcellentBidDao {
    public int deleteAll() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement("delete from excellent_bid");
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, null);
        }
        return 0;
    }

    public int insert(int e_id, String name, int score) {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement("insert into excellent_bid (e_id,name,score) values (?,?,?)");
            pre.setInt(1, e_id);
            pre.setString(2, name);
            pre.setInt(3, score);
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, null);
        }
        return 0;
    }
}
