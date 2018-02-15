package edu.seattleu.kaurj7.emailCount;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class EmailCountReducer extends Reducer<Text, Text, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		//uses a HashSet data structure   
		Set<String> set = new HashSet<String>();
		//calculates the email IDS and its count.
		for (Text val : values) {
			set.add(val.toString());
		}
		sum = set.size();
		result.set(sum);
		context.write(key, result);
	}
}
