package Report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Delta {
	static String event;
	static String event1;
	
	public static void sspDelta() throws IOException{
		
		String[] url = {"10.160.200.141", "10.160.200.142", "10.160.200.143"};
		
				BufferedReader br1 = null;
				BufferedReader br2 = null;
				BufferedWriter bw = null;
				String line1 = "";
				String line2 = "";
				
				String cvsSplitBy = ",";
				
				for(int i=0;i<=2;i++){
					
					String beofeTest = TakeScreenshot_Test.filePath + url[i] + "_BeforeTest_Status.csv";
					String afterTest = TakeScreenshot_Test.filePath + url[i] + "_AfterTest_Status.csv";
					
					bw = new BufferedWriter(new FileWriter(TakeScreenshot_Test.filePath + url[i] + "_Delta.csv"));

					br1 = new BufferedReader(new FileReader(beofeTest));
					br2 = new BufferedReader(new FileReader(afterTest));
				
				while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
					String[] data1 = line1.split(cvsSplitBy);
					String[] data2 = line2.split(cvsSplitBy);
					
										
					if(data1[0].contains("Activity Service Events") && data2[0].contains("Activity Service Events")){
						event = data1[0];
						bw.write(event + "\n");
						bw.append("name" + "," + "BeforeTest_avg" + "," + "AfterTest_avg" + "," + "Delta_avg" + "," + "BeforeTest_total" + "," + "AfterTest_total" + "," + "Delta_total" + "\n");
					}
					
					if(data1[0].contains("Item Service Events") && data2[0].contains("Item Service Events")){
						event = data1[0];
						bw.write(event + "\n");
						bw.append("name" + "," + "BeforeTest_avg" + "," + "AfterTest_avg" + "," + "Delta_avg" + "," + "BeforeTest_total" + "," + "AfterTest_total" + "," + "Delta_total" + "\n");
					}
					
					if (data1.length>10 && data2.length>10){
						if (!data2[9].contains("avg") && !data1[9].contains("avg")){
						bw.append(data1[0]);
						bw.append(",");
						bw.append(data1[9]);
						bw.append(",");
						bw.append(data2[9]);
						bw.append(",");
						bw.append(Float.toString(Float.parseFloat(data2[9])-Float.parseFloat(data1[9])));
						bw.append(",");
						bw.append(data1[17]);
						bw.append(",");
						bw.append(data2[17]);
						bw.append(",");
						bw.append(Float.toString(Float.parseFloat(data2[17])-Float.parseFloat(data1[17])));
						bw.append("\n");
						}
					}
				}
				br1.close();
				br2.close();
				bw.close();
			}
		}
	}
