import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IncrementalList {
	public static void main (String[] args) {
		String strPath = "";
		File orgFile = null;
		File updFile = null;
	        final String STR_USAGE = "Usage:java IncrementalList OriginalFilePath UpdateFilePath";
	        final String STR_FILE_NOT_FOUND = " is not found.";
	        //final String strEncod = "utf-8";
	        final String strEncod = "sjis";
		String strOriginalPath = "";
		String strUpdatePath = "";
		List<String> list = new ArrayList<>();

		if (args.length == 2 ) {
			strOriginalPath = args[0];
			strUpdatePath = args[1];
			orgFile = new File( strOriginalPath );
			updFile = new File( strUpdatePath );
			if (!orgFile.exists()){
				System.out.println( strOriginalPath + STR_FILE_NOT_FOUND );
				return;
			} 
			if (!updFile.exists()){
				System.out.println( strUpdatePath + STR_FILE_NOT_FOUND );
				return;
			} 
		} else if (args.length <= 1) {
	                System.out.println(STR_USAGE);
	                return;
        	}
		try {


			BufferedReader orgBr = new BufferedReader(new InputStreamReader(new FileInputStream(orgFile), strEncod));
			BufferedReader updBr = new BufferedReader(new InputStreamReader(new FileInputStream(updFile), strEncod));
			String line = null;
			while ((line = orgBr.readLine()) != null) {
				list.add( line );
			}
			while ((line = updBr.readLine()) != null) {
				if( list.indexOf( line ) == -1 ) {
					System.out.println( line );
				}
			}
			orgBr.close();
			updBr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}