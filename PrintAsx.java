import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PrintAsx {
	public static void main (String[] args) {
		String strDirectoryPath = "";
		String strFileListPath = "";
		File listFile = null;
		File directoryFile = null;
	        final String STR_USAGE = "Usage:java PrintAsx DirectoryPath VideoListFilePath";
	        final String STR_FILE_NOT_FOUND = " is not found.";
	        final String strEncod = "sjis";
		String strPath = "";
		if (args.length == 2 ) {
			strDirectoryPath = args[0];
			strFileListPath = args[1];
			listFile = new File( strFileListPath );
			directoryFile = new File( strDirectoryPath );
			if (!directoryFile.exists()){
				System.out.println( strDirectoryPath + STR_FILE_NOT_FOUND );
				return;
			} 
			if (!listFile.exists()){
				System.out.println( strFileListPath + STR_FILE_NOT_FOUND );
				return;
			} 
		} else if (args.length <= 1) {
	                System.out.println(STR_USAGE);
	                return;
		} else if (args.length > 2) {
	                System.out.println(STR_USAGE);
	                return;
		}
		try {


			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(listFile), strEncod));
			String line = null;
			System.out.println("<asx version = \"3.0\" >");
			while ((line = br.readLine()) != null) {
				System.out.println("	<entry>");

				System.out.print("		<title>");
				System.out.print( line );
				System.out.println("</title>");

				System.out.print("		<ref href = \"");
				System.out.print( directoryFile  + "\\" + line );
				System.out.println("\" />");

				System.out.println("		<duration value = \"0\" />");

				System.out.println("	</entry>");
			}
			System.out.println("</asx>");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}