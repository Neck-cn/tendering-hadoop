package cn.ych.tendering.excellent;

import cn.ych.tendering.dao.ExcellentDao;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondExcellentBidReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

    private final IntWritable k = new IntWritable();
    private final Text v = new Text();
    private final ExcellentDao excellentDao;

    public SecondExcellentBidReducer() {
        this.excellentDao = new ExcellentDao();
        excellentDao.deleteAll();
    }

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            String string = value.toString();
            String[] split = string.split(":");
            k.set(Integer.parseInt(split[0]));
            v.set(split[1]);
            excellentDao.insert(k.get(), v.toString(), key.get(), "bid");
            v.set(split[1] + "," + key.get());
            context.write(k, v);
        }
    }
}
