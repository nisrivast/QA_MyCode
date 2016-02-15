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
	static String percentile;
	
public static void csv(){

	      String fileName = TakeScreenshot_Test.filePath + "Aggregate.csv";

			try {
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
			 catch (IOException e) {
				e.printStackTrace();
			} 
		}	
 
}	

