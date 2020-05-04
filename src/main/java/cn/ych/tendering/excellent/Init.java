package cn.ych.tendering.excellent;

import cn.ych.tendering.dao.BidDao;
import cn.ych.tendering.dao.ReportDao;
import cn.ych.tendering.dao.TenderingDao;
import cn.ych.tendering.pojo.Bid;
import cn.ych.tendering.pojo.Report;
import cn.ych.tendering.pojo.Tendering;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Init {
    private File file;
    public static String bidFileName = "input-bid-";
    public static String tenderingFileName = "input-tendering-";

    public void initBid() throws IOException {
        bidFileName += System.currentTimeMillis();
        file = new File(bidFileName);

        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        List<Bid> bidList = new BidDao().selectAll();
        for (Bid bid : bidList) {
            fileWriter.append("bid ").append(String.valueOf(bid.getE_id())).append(":").append(bid.getE_name()).append("\n");
        }

        List<Report> reportList = new ReportDao().selectAll();
        for (Report report : reportList) {
            fileWriter.append("report ").append(String.valueOf(report.getE_id())).append(":").append(report.getE_name()).append("\n");
        }

        List<Tendering> tenderingList = new TenderingDao().selectAll();
        for (Tendering tendering : tenderingList) {
            fileWriter.append("tendering ").append(String.valueOf(tendering.getWin_id())).append(":").append(tendering.getName()).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void initTendering() throws IOException {
        tenderingFileName += System.currentTimeMillis();
        file = new File(tenderingFileName);

        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        List<Tendering> tenderingList = new TenderingDao().selectAllTendering();
        for (Tendering tendering : tenderingList) {
            //e_id,e_name,end_time,win_id
            fileWriter.append(String.valueOf(tendering.getE_id())).append(',').append(tendering.getName())
                    .append(',').append(tendering.getEnd_time()).append(',').append(String.valueOf(tendering.getWin_id())).append('\n');
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
