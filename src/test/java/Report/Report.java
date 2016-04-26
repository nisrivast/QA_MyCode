package Report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
 
public class Report {

	static String startTime = "";
	
	public static void createHTML() throws IOException{
		
		File newfile = new File(GenerateReport_Test.filePath + "FinalReport.html");
	    boolean bool;
	    String frontEnd = "";
	    CSVReader.csv();
	    CSVReader.getBuild();
	    CSVReader.writeComparisionCSV();
	    CSVReader.readcomparisionCSV();
	      
	     try{
	         // creates new file in the system
	         bool = newfile.createNewFile();
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }

	    BufferedReader br = null;
		String line = "";

	    BufferedReader br2 = null;
		String line2 = "";

			br = new BufferedReader(new FileReader("Format.html"));
			br2 = new BufferedReader(new FileReader(GenerateReport_Test.filePath + "StartTime.txt"));

			while ((line = br.readLine()) != null) {
				frontEnd = frontEnd.concat(line);
				}
			br.close();
			while ((line2 = br2.readLine()) != null) {
				startTime = startTime.concat(line2);
				}
			br2.close();
	
	      frontEnd = frontEnd.replace("avgrestime", CSVReader.restime1);
	      frontEnd = frontEnd.replace("transactions", CSVReader.ttransactions);
	      frontEnd = frontEnd.replace("error", CSVReader.errorrate).replace("percentile", CSVReader.percentile);
	      
	      frontEnd = frontEnd.replace("rt_zero", CSVReader.rt_0).replace("rt_one", CSVReader.rt_1);
	      frontEnd = frontEnd.replace("rt_two", CSVReader.rt_2).replace("rt_three", CSVReader.rt_3);
	      frontEnd = frontEnd.replace("rt_four", CSVReader.rt_4);
	      
	      frontEnd = frontEnd.replaceAll("b_0", CSVReader.bn_0).replaceAll("b_1", CSVReader.bn_1);
	      frontEnd = frontEnd.replaceAll("b_2", CSVReader.bn_2).replaceAll("b_3", CSVReader.bn_3);
	      frontEnd = frontEnd.replaceAll("b_4", CSVReader.bn_4);
	      
	      frontEnd = frontEnd.replaceAll("begintime", startTime);
	      frontEnd = frontEnd.replaceAll("finaltime", String.valueOf(GenerateReport_Test.finaltime));

	     FileWriter fw;
				fw = new FileWriter(newfile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
		        bw.write(frontEnd);
		        bw.close();	
	}
}
