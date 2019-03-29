import java.io.*;
public class InputStreamTest {
	public static void main (String[] args) {
	        final String STR_ENCODE = "sjis";
		try {
			InputStreamReader isr = new InputStreamReader(System.in, STR_ENCODE);
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			line = br.readLine();
			System.out.println( line );
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}