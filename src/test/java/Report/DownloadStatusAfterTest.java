package Report;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

	public class DownloadStatusAfterTest {

		public static void statusPagesafterTest() throws IOException {

			String[] url = {"10.160.200.141", "10.160.200.142", "10.160.200.143"};

			for(int i=0; i<=2; i++){
				String fileName = TakeScreenshot_Test.filePath + url[i] + "_AfterTest_Status.csv";
				URL link = new URL("http://" + url[i] + "/activityservice/csv-stats.jsp");

				//download
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
			}		 
			System.out.println("Finished");

		}

	}

