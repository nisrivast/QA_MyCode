package qait;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.testng.annotations.Test;

public class BeginTest {

	static long startTime = System.currentTimeMillis() / 1000L;
	static String filePath = "C:/Users/admin/.jenkins/jobs/CXP_DemoLoadTest/workspace";

	@Test
	public static void beforeTest() throws IOException{
		statusPages();
		startTimeFile();
	}

	public static void statusPages() throws IOException {

		String[] url = {"10.160.200.141", "10.160.200.142", "10.160.200.143"};

		for(int i=0; i<=2; i++){

			String fileName = filePath + url[i] + "_BeforeTest_Status.csv";
			URL link = new URL("http://" + url[i] + "/activityservice/csv-stats.jsp");

			//download server status pages
			InputStream in = new BufferedInputStream(link.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();

			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(response);
			fos.close();
			//End download
		}		 
		System.out.println("Finished");

	}

	public static void startTimeFile(){
  
		boolean bool = false;
		String fileName = filePath + "StartTime.txt";
		File file = new File(fileName);

		try{
			// creates new file to save start time of test
			bool = file.createNewFile();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.valueOf(startTime));
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
