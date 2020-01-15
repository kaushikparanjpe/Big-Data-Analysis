package counter;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		conf.addResource(new Path("/usr/local/bin/hadoop-2.8.5/etc/hadoop/core-site.xml"));
		conf.addResource(new Path("/usr/local/bin/hadoop-2.8.5/etc/hadoop/hdfs-site.xml"));
		FileSystem fs = FileSystem.get(conf);
		URI uri=URI.create ("hdfs://127.0.0.1:9000/");
		Path src = new Path("/home/kaushikp/Downloads/nyse/NYSE");
		Path dst = new Path(uri + "/NYSE_input");
		fs.copyFromLocalFile(src, dst);
		Path trg = new Path(uri + "/NYSE_output/Shit.txt");
		/*
		//fs.create(trg, true);
		FileStatus[] fstatus = fs.listStatus(dst);
		Path[] psrcs = FileUtil.stat2Paths(fstatus);
		System.out.println(trg);
		fs.concat(trg, psrcs);
		*/
		FileUtil.copyMerge(fs, dst, fs, trg, false, conf, "");
		fs.close();
		
	}

}
