import java.math.BigDecimal;
import java.math.RoundingMode;
public class Percentage {
	public static void main( String[] args ) {
		double d1 = 1000;
		double d2 = 1100;
		double d3 = 1.1;
		double d4 = 0.9;
		double d5 = 900;
		double d6 = 100;
		double d7 = 10;
		double d8 = 100;

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		BigDecimal b2 = new BigDecimal(String.valueOf(d2));
		BigDecimal b3 = new BigDecimal(String.valueOf(d3));
		BigDecimal b4 = new BigDecimal(String.valueOf(d4));
		BigDecimal b5 = new BigDecimal(String.valueOf(d5));
		BigDecimal b6 = new BigDecimal(String.valueOf(d6));
		BigDecimal b7 = new BigDecimal(String.valueOf(d7));
		BigDecimal b8 = new BigDecimal(String.valueOf(d8));

		//税込1100の税抜は1000
		//1100÷1.1=1000
		BigDecimal t4 =(b2.divide(b3)); 
		System.out.println( "1割引きの元値 "  + t4.toPlainString());

		//税込金額の計算は1.1を掛ける。
		//1000×1.1=1100
		BigDecimal t5 = b1.multiply(b3);
		System.out.println( "1割増し値 "  + t5.toPlainString());


		//1割引きは0.9を掛ける方が一般的なようだ
		//1000×(1-0.1)
		BigDecimal t6 = b1.multiply(b4);
		System.out.println( "1割引き値 "  + t6.toPlainString());

		//値引き前の数値の計算
		//1割引きで900の元値の求め方
		//900×(100÷(100-10))
		BigDecimal t7 = b5.multiply( b6.divide( b8.subtract(b7), 10, RoundingMode.UP));
		System.out.println( "1割引き前値 "  + t7.toPlainString());
	}
}