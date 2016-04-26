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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


public class CSVReader {
	
	static String restime1, comp_restime="";
	static String errorrate;
	static String ttransactions;
	static String percentile;

	static String cvsSplitBy = ",";
	
	static String bnumber = "";
	static String successCheck = "";
	static List<String> successBuilds = new ArrayList<String>();	
	
	static String rt_0 ="" , bn_0 = "";
	static String rt_1 = "", bn_1 = "";
	static String rt_2 = "", bn_2 = "";
	static String rt_3 = "", bn_3 = "";
	static String rt_4 = "", bn_4 = "";

public static void getBuild() throws IOException{
	URL url = new URL(GenerateReport_Test.jenkinsURL + "lastSuccessfulBuild/");
	URLConnection con = url.openConnection();
	InputStream in = con.getInputStream();
	String encoding = con.getContentEncoding();
	encoding = encoding == null ? "UTF-8" : encoding;
	String body = IOUtils.toString(in, encoding);
	bnumber = bnumber.concat((StringUtils.substringBetween(body, "Build #", "(").trim()));
	successBuild();
}

public static void successBuild() throws IOException{
	int count=1;
	for(int i=0;count<6;i++){
		
		URL url1 = new URL(GenerateReport_Test.jenkinsURL + (Integer.parseInt(bnumber)-i)+"/");
		URLConnection con = url1.openConnection();
		InputStream in1 = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body1 = IOUtils.toString(in1, encoding);
		successCheck = successCheck.concat(StringUtils.substringBetween(body1, "buildTimeTrend", "Build #"));

		if(successCheck.contains("Success")){
					successBuilds.add(Integer.toString(Integer.parseInt(bnumber)-i));
			count = count+1;
			System.out.println(i + ": " + successBuilds);
		}
		successCheck = "";
	}	
}
	
public static void csv() throws IOException{

	      String fileName = GenerateReport_Test.filePath + "Aggregate.csv";

				BufferedReader br = null;
				String line = "";
				br = new BufferedReader(new FileReader(fileName));
				
				while ((line = br.readLine()) != null) {
					String[] data = line.split(cvsSplitBy);
					if (data[0].equalsIgnoreCase("Total")){
						ttransactions = data[1];
						restime1 = Double.toString((Double.parseDouble(data[2])/1000));
						percentile = Double.toString((Double.parseDouble(data[4])/1000));
						errorrate = data[7];
					}
				}
				br.close();
		}

public static void writeComparisionCSV() throws IOException{
	
	BufferedWriter bw2 = null;
	bw2 = new BufferedWriter(new FileWriter(GenerateReport_Test.filePath + "comparison.csv"));
	
	bw2.write("Build" + "," + successBuilds.get(0) + "," + successBuilds.get(1) + "," + successBuilds.get(2));
	bw2.append("," + successBuilds.get(3) + "," + successBuilds.get(4) + "\n");
	bw2.append("ResponseTime");
	bw2.append(",");

		for (String i: successBuilds){
		String aggregateCSV = GenerateReport_Test.aggrgateCSVPath  + i + "/" + "Aggregate.csv";

		BufferedReader br2 = null;
		String line2 = "";

		br2 = new BufferedReader(new FileReader(aggregateCSV));

		while ((line2 = br2.readLine()) != null) {
			String[] data = line2.split(cvsSplitBy);
			
			if (data[0].equalsIgnoreCase("Total")){
				comp_restime = data[2];	
			}
		}
		br2.close();
		bw2.append(comp_restime);
		if (!i.equalsIgnoreCase(successBuilds.get(4))){
			bw2.append(",");
		}
		
		if (i.equalsIgnoreCase(successBuilds.get(4))){
			bw2.append("\n");
		}
	}
	bw2.close();
}

public static void readcomparisionCSV() throws IOException{
	String compCSV = GenerateReport_Test.filePath + "comparison.csv";

	BufferedReader br = null;
	String line = "";
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
