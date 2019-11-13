import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class Pay {
	public static void main( String[] args ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateTo = null;
		Date dateFrom = null;

		// ���t���쐬���܂��B
		try {
			dateFrom = sdf.parse("2002/04/01 00:00:00");
			dateTo = sdf.parse("2019/11/13 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// ���t��long�l�ɕϊ����܂��B
		long dateTimeTo = dateTo.getTime();
		long dateTimeFrom = dateFrom.getTime();

		// �����̎��Ԃ��Z�o���܂��B
		long dayDiff = ( dateTimeTo - dateTimeFrom  ) / (1000 * 60 * 60 );

		System.out.print( "���� : " + dayDiff );
		System.out.println( "���z : " + dayDiff * 2000000 );
	}
}