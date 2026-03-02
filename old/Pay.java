import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class Pay {
	public static void main( String[] args ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateTo = null;
		Date dateFrom = null;

		try {
			dateFrom = sdf.parse("2002/04/01 00:00:00");
			dateTo = sdf.parse("2023/02/23 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long dateTimeTo = dateTo.getTime();
		long dateTimeFrom = dateFrom.getTime();

		long dayDiff = ( dateTimeTo - dateTimeFrom  ) / (1000 * 60 * 60 );

		System.out.print( "時間 : " + dayDiff );
		System.out.println( "金額 : " + dayDiff * 2000000 );
	}
}