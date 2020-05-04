package cn.ych.tendering.utils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * TODO 	 IntWritable类的倒叙排序
 * @author   administrator
 * @Date	 2019年10月7日
 */
public class IntWritableComparator extends WritableComparator {

    /*
     * 重写构造方法，定义比较类 IntWritable
     */
    public IntWritableComparator() {
        super(IntWritable.class, true);
    }
    /*
     * 重写compare方法，自定义比较规则
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        //向下转型
        IntWritable ia = (IntWritable) a;
        IntWritable ib = (IntWritable) b;
        return ib.compareTo(ia);
    }
}