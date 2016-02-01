package Report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
 
public class Report {
	
	static long endTime = System.currentTimeMillis() / 1000L;
	static String startTime = "";
	
	public static void createHTML(){
		
		File newfile = new File("FinalReport.html");
	    boolean bool;
	    String frontEnd = "";
	    CSVReader.csv();
	      
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
			br2 = new BufferedReader(new FileReader("StartTime.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while ((line = br.readLine()) != null) {
				frontEnd = frontEnd.concat(line);
				}
			br.close();
			while ((line2 = br2.readLine()) != null) {
				//startTime = startTime.concat(line);
				
				startTime = startTime.concat(line2);
				}
			System.out.println(startTime);
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	      frontEnd = frontEnd.replace("avgrestime", CSVReader.restime1);
	      frontEnd = frontEnd.replace("transactions", CSVReader.ttransactions);
	      frontEnd = frontEnd.replace("error", CSVReader.errorrate);
	      System.out.println("***" + endTime);
	      System.out.println("***" + startTime);
	      frontEnd = frontEnd.replaceAll("begintime", startTime);
	      frontEnd = frontEnd.replaceAll("endtime", String.valueOf(endTime));

	     FileWriter fw;
			try {
				fw = new FileWriter(newfile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
		        bw.write(frontEnd);
		        bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
