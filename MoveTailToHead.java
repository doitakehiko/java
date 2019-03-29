import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MoveTailToHead {
	public static void printMoveTailToHead ( String line ) {
		String[] strArrayData = line.split(" ", -1);
		String[] strArrayResult = new String[strArrayData.length];
		strArrayResult[0] = strArrayData[strArrayData.length - 1];
		for (int i = 1; i < strArrayData.length; i++ )
		{
			strArrayResult[i] = strArrayData[i - 1];
		}

		for (int i = 0; i < strArrayResult.length; i++ )
		{
			System.out.print( strArrayResult[i] + " ");
		}
		System.out.println("");
		return;
	}
	public static void main (String[] args) {
		String strPath = "";
	        final String strEncod = "sjis";
		if (args.length == 1 ) {
			strPath = args[0];
			String  strFileExit = strPath + "‚ª‚ ‚è‚Ü‚¹‚ñB";
			File file = new File( strPath );
			if (!file.exists()){
				System.out.println( strFileExit );
				return;
			} 
			try {


				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), strEncod));
				String line = null;
				while ((line = br.readLine()) != null) {
					printMoveTailToHead( line );
				}
			        br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ( args.length == 0 ) {
			try {
				if (System.in.available() != 0) {
					InputStreamReader isr = new InputStreamReader(System.in, strEncod);
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					while ((line = br.readLine()) != null ) {
						printMoveTailToHead( line );
					}
				} else {
					System.out.println("Not Found: pipied input");
				}


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}