package cn.ych.tendering.excellent.tendering;

import cn.ych.tendering.dao.ExcellentTenderingDao;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExcellentTenderingReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

    Text v = new Text();
    long currentTime = System.currentTimeMillis();
    ExcellentTenderingDao excellentTenderingDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ExcellentTenderingReducer() {
        excellentTenderingDao = new ExcellentTenderingDao();
        excellentTenderingDao.deleteAll();
    }

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
        int sum = 0;
        int win = 0;
        int fail = 0;
        String e_name = null;
        for (Text value : values) {
            String[] split = value.toString().split(",");
            e_name = split[0];
            long endTime = 0;
            try {
                endTime = sdf.parse(split[1]).getTime();
            } catch (ParseException e) {
                throw new RuntimeException("时间格式错误");
            }
            if (Integer.parseInt(split[2]) != 0) {
                win++;
            } else if (currentTime > endTime) {
                fail++;
            }
            sum++;
        }
        v.set(win + "," + fail + "," + "," + sum + "," + win / (sum + 0.0) + "," + fail / (sum + 0.0));
        excellentTenderingDao.insert(key.get(), e_name, win, fail, sum, win / (sum + 0.0), fail / (sum + 0.0));
        context.write(key, v);
    }
}
