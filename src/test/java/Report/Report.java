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
		
		File newfile = new File(TakeScreenshot_Test.filePath + "FinalReport.html");
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
		
		try {
			br = new BufferedReader(new FileReader("Format.html"));
			br2 = new BufferedReader(new FileReader(TakeScreenshot_Test.filePath + "StartTime.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

			while ((line = br.readLine()) != null) {
				frontEnd = frontEnd.concat(line);
				}
			br.close();
			while ((line2 = br2.readLine()) != null) {
				startTime = startTime.concat(line2);
				}
			br.close();
	
	      frontEnd = frontEnd.replace("avgrestime", CSVReader.restime1);
	      frontEnd = frontEnd.replace("transactions", CSVReader.ttransactions);
	      frontEnd = frontEnd.replace("error", CSVReader.errorrate).replace("percentile", CSVReader.percentile);
	      
	      frontEnd = frontEnd.replace("rt_zero", CSVReader.rt_0).replace("rt_one", CSVReader.rt_1);
	      frontEnd = frontEnd.replace("rt_two", CSVReader.rt_2).replace("rt_three", CSVReader.rt_3);
	      frontEnd = frontEnd.replace("rt_four", CSVReader.rt_4);
	      
	      frontEnd = frontEnd.replace("b_0", CSVReader.bn_0).replace("b_1", CSVReader.bn_1);
	      frontEnd = frontEnd.replace("b_2", CSVReader.bn_2).replace("b_3", CSVReader.bn_3);
	      frontEnd = frontEnd.replace("b_4", CSVReader.bn_4);
	      
	      frontEnd = frontEnd.replaceAll("begintime", startTime);
	      frontEnd = frontEnd.replaceAll("endtime", String.valueOf(TakeScreenshot_Test.endTime));

	     FileWriter fw;
				fw = new FileWriter(newfile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
		        bw.write(frontEnd);
		        bw.close();	
	}
}
