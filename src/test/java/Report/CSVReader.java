package Report;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


public class CSVReader {
	
	static String restime1, comp_restime;
	static String errorrate;
	static String ttransactions;
	static String percentile;
	
	static String bnumber; //= System.getProperty("BUILD_NUMBER");
	static String rt_0, bn_0;
	static String rt_1, bn_1;
	static String rt_2, bn_2;
	static String rt_3, bn_3;
	static String rt_4, bn_4;

public static void getBuild() throws IOException{
	URL url = new URL("http://localhost:8080/job/CXP_DemoLoadTest/lastSuccessfulBuild/");
	URLConnection con = url.openConnection();
	InputStream in = con.getInputStream();
	String encoding = con.getContentEncoding();
	encoding = encoding == null ? "UTF-8" : encoding;
	String body = IOUtils.toString(in, encoding);
	bnumber = bnumber.concat((StringUtils.substringBetween(body, "Build #", "(").trim()));
}
	
public static void csv() throws IOException{

	      String fileName = TakeScreenshot_Test.filePath + "Aggregate.csv";

				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				br = new BufferedReader(new FileReader(fileName));
				
				while ((line = br.readLine()) != null) {
					String[] data = line.split(cvsSplitBy);
					if (data[0].equalsIgnoreCase("Total")){
						ttransactions = data[1];
						restime1 = data[2];
						percentile = data[4];
						errorrate = data[7];
					}
				}
				br.close();
		}

public static void writeComparisionCSV() throws IOException{
	
	BufferedWriter bw2 = null;
	bw2 = new BufferedWriter(new FileWriter(TakeScreenshot_Test.filePath + "comparision.csv"));
	
	bw2.write("Build" + "," + bnumber + "," + Integer.toString(Integer.parseInt(bnumber)-1) + "," + Integer.toString(Integer.parseInt(bnumber)-2));
	bw2.append("," + Integer.toString(Integer.parseInt(bnumber)-3) + "," + Integer.toString(Integer.parseInt(bnumber)-4) + "\n");
	bw2.append("ResponseTime" + ",");

	for (int i=Integer.parseInt(bnumber); i>Integer.parseInt(bnumber)-4; i--){
		String aggregateCSV = TakeScreenshot_Test.aggrgateCSVPath  + i + "/" + "Aggregate.csv";
		
		BufferedReader br2 = null;
		String line2 = "";
		String cvsSeparator = ",";

		br2 = new BufferedReader(new FileReader(aggregateCSV));

		while ((line2 = br2.readLine()) != null) {
			String[] data = line2.split(cvsSeparator);
			if (data[0].equalsIgnoreCase("Total")){
				comp_restime = data[2];
			}
			bw2.append(comp_restime);
			if (i>Integer.parseInt(bnumber)-4){
				bw2.append(",");
			}
			br2.close();
		}
	}
	bw2.close();
}

public static void readcomparisionCSV() throws IOException{
	String compCSV = TakeScreenshot_Test.filePath + "comparision.csv";

	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	br = new BufferedReader(new FileReader(compCSV));
	
	while ((line = br.readLine()) != null) {
		String[] data = line.split(cvsSplitBy);
		if (data[0].equalsIgnoreCase("Build")){
			bn_0 = data[1];
			bn_1 = data[2];
			bn_2 = data[3];
			bn_3 = data[4];
			bn_4 = data[5];
		}
		
		if (data[0].equalsIgnoreCase("ResponseTime")){
			rt_0 = data[1];
			rt_1 = data[2];
			rt_2 = data[3];
			rt_3 = data[4];
			rt_4 = data[5];
		}
	}
	br.close();
}
}	
