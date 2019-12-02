import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Recursion {
	public static void main (String[] args) {
		String strPath = "";
		File readFile = null;
	        final String STR_USAGE = "Usage:java IncrementalList filename";
	        final String STR_FILE_NOT_FOUND = " is not found.";
	        //final String strEncod = "utf-8";
	        final String strEncod = "sjis";
		final String strSplit = "ÅA";
		String strReadPath = "";
		int count = 0;
		List<String> list = new ArrayList<>();

		if (args.length == 1 ) {
			strReadPath = args[0];
			readFile = new File( strReadPath );
			if (!readFile.exists()){
				System.out.println( readFile + STR_FILE_NOT_FOUND );
				return;
			} 
		} else if (args.length == 0) {
	                System.out.println(STR_USAGE);
	                return;
        	}
		try {


			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), strEncod));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] strWord = line.split(strSplit, 0);
				for (int i = 0 ; i < strWord.length ; i++){
					if ( strWord[i] != "" ) {
						list.add( strWord[i] );;
					}
				}
			}
			
			for ( int i = 0; i < list.size(); i++ ) {
				for ( int n = 0; n < list.size(); n++ ) {
					if ( list.get( i ).equals(list.get( n ))) {
						count++;
 					}
				}
				if ( count == 1 ) {
					System.out.println(list.get(i));
				}
				count = 0;
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}