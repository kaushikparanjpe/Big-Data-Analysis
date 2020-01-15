package NaiveBayes_;

import java.io.IOException;

import java.util.Map;
import java.util.List; 
import scala.Tuple2; 
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD; 
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import edu.umd.cloud9.io.pair.PairOfStrings;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.sql.Dataset;



public class Naive_bayes_driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkConf conf = new SparkConf().setAppName("NB").setMaster("local[*]").set("spark.ui.port","4040");;
	    JavaSparkContext jsc = new JavaSparkContext(conf);
		String path = "localpath";
		JavaRDD<LabeledPoint> inputData = MLUtils.loadLabeledPoints(jsc.sc(), path).toJavaRDD();
		JavaRDD<LabeledPoint>[] tmp = inputData.randomSplit(new double[]{0.6, 0.4});
		JavaRDD<LabeledPoint> training = tmp[0]; // training set
		JavaRDD<LabeledPoint> test = tmp[1]; // test set
		NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);
		JavaPairRDD<Double, Double> predictionAndLabel =
		  test.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));
		// Save and load model
		model.save(jsc.sc(), "localotuput_path");
		NaiveBayesModel sameModel = NaiveBayesModel.load(jsc.sc(), "localotuput_path");
		
		double accuracy =
				  predictionAndLabel.filter(pl -> pl._1().equals(pl._2())).count() / (double) test.count();
				
		System.out.println("The final accuracy is"+accuracy);
//		predictionAndLabel.foreach(data -> {
//		    System.out.println("final model="+data._1() + "final label=" + data._2());
//		}); 
	}

}
