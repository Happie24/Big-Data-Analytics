package org.bdaproject.Mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class q2map extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Split the line into fields using comma as the delimiter
        String[] fields = value.toString().split(",");

        // Check if there are enough fields to access the "Search Type" column (index 5)
        if (fields.length > 6) {
            String searchType = fields[6].trim(); // Column index 5: Search Type

            // Skip lines where the search type is empty
            if (!searchType.isEmpty()) {
                context.write(new Text(searchType), one);
            }
        }
    }
}
