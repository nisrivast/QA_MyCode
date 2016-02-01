package Report;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CSVReader {
	
	static String restime1;
	static String errorrate;
	static String ttransactions;
	
public static void csv(){

	      String fileName = "Aggregate.csv";

			try {
				
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				br = new BufferedReader(new FileReader(fileName));
				
				while ((line = br.readLine()) != null) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					if (data[0].equalsIgnoreCase("Total")){
						ttransactions = data[1];
						restime1 = data[2];
						errorrate = data[7];
					}
				}
				br.close();
			}	
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}	
 
}	

