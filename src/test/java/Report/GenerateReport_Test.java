package Report;

import org.testng.annotations.Test;
import java.io.IOException;

public class GenerateReport_Test {

	static long finaltime = System.currentTimeMillis() / 1000L;

	static String filePath = "C:/Users/nitinsrivastava/.jenkins/jobs/CXP_DemoLoadTest/workspace/";
	static String aggrgateCSVPath = "C:/Users/nitinsrivastava/.jenkins/jobs/CXP_DemoLoadTest/builds/";
	static String jenkinsURL = "http://localhost:8080/job/CXP_DemoLoadTest/";
	
	@Test
	public static void loadTestReport() throws IOException {

		DownloadStatusAfterTest.statusPagesafterTest();
		Delta.sspDelta();
		GenerateReport_Test ts = new GenerateReport_Test();
		try{
			Report.createHTML();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
}
