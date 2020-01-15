package DistrictCodeJoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistrictJoin extends Mapper<LongWritable, Text, Text, NullWritable>{

	public HashMap<String, String> countsData = new HashMap<>();
	public Text outval = new Text();
	NullWritable n = NullWritable.get();
	String joinType = null;
	String file_loc = null;
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		String data = value.toString();
		String k1 = data.split(",")[11];
		String v1 = countsData.get(k1);
		if(v1 != null) {
			outval.set(value.toString()+ "@,"+v1);
			context.write(outval, n);
		}
			
		
	}
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		joinType = context.getConfiguration().get("join.type");
		file_loc = context.getConfiguration().get("file.loc");
		
		
		
		try{
			URI[] paths = context.getCacheFiles();
    				//DistributedCache.getLocalCacheFiles(context.getConfiguration());
    	//System.out.println(paths.length);
			if(paths != null && paths.length > 0) {
    			for(URI path : paths) {
    				readFile(new Path(path));
    			}
    		}
    	} catch(IOException ex) {
    		System.err.println("Exception in mapper setup: " + ex.getMessage());
    	}
	}
	
	private void readFile(Path filePath) {
		try{
			//System.out.println(filePath.toString());
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toString()));
//			File n = new File(filePath.toString());
//			FileInputStream fis = new FileInputStream(n);
//			byte[] data = new byte[(int)n.length()];
//			fis.read(data);
//			fis.close();
//			String str = new String(data, "UTF-8");
			//System.out.println(str);
			//Scanner sc = new Scanner(new FileReader(filePath.toString()));
			String line = null;
			//line = bufferedReader.readLine();
			//System.out.println(line);
			//line = bufferedReader.readLine();
			//System.out.println(line);
			///int k = 1;
			while((line = bufferedReader.readLine()) != null) {
				//line = bufferedReader.readLine();
				String code = line.split(":")[0];
				String count = line.split(":")[1];
				//line = sc.nextLine();
		//		System.out.println(line);
				countsData.put(code, count);
				//System.out.println(line);
			}
		} catch(IOException ex) {
			System.err.println("Exception while reading stop words file: " + ex.getMessage());
		}
	}
	
}
