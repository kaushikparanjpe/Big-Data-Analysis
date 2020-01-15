package counter;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class NKPartitioner extends Partitioner <Compositekeywritable, NullWritable> {

	@Override
	public int getPartition(Compositekeywritable arg0, NullWritable arg1, int arg2) {
		// TODO Auto-generated method stub
		int hash = arg0.getAccessdate().hashCode();
		int partition = hash % arg2;
		return partition;
	}

	
	
}
