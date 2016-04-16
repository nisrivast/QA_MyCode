package Report;

import org.testng.annotations.Test;
import java.io.IOException;

public class TakeScreenshot_Test {

	static long finaltime = System.currentTimeMillis() / 1000L;

	static String filePath = "C:/Users/nitinsrivastava/.jenkins/jobs/CXP_DemoLoadTest/workspace/";
	static String aggrgateCSVPath = "C:/Users/nitinsrivastava/.jenkins/jobs/CXP_DemoLoadTest/builds/";
	
	@Test
	public static void loadTestReport() throws IOException {

		DownloadStatusAfterTest.statusPagesafterTest();
		Delta.sspDelta();
		TakeScreenshot_Test ts = new TakeScreenshot_Test();
		try{
			Report.createHTML();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}

	/*public void takeScreenshot(String title) {
		File file = new File(filePath);
		boolean exists = file.exists();
		if (!exists) {
			new File(filePath).mkdir();
		}

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			String saveImgFile = filePath + title + "_screenshot.png";
			System.out.println("[INFO]: Save Image File Path : " + saveImgFile);
			FileUtils.copyFile(scrFile, new File(saveImgFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
