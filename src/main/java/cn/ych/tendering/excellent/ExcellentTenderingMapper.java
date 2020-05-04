package cn.ych.tendering.excellent;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ExcellentTenderingMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text k = new Text();
    IntWritable v = new IntWritable(0);

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String input = value.toString();
        if (StringUtils.isEmpty(input)) {
            return;
        }
        String[] s = input.split(",");
        k.set(s[1]);
        switch (s[0]) {
            case "bid":
                v.set(1);
                break;
            case "report":
                v.set(-3);
                break;
            case "tendering":
                v.set(2);
                break;
            default:return;
        }
        context.write(k, v);
    }
}
