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

		//�ō�1100�̐Ŕ���1000
		//1100��1.1=1000
		BigDecimal t4 =(b2.divide(b3)); 
		System.out.println( "1�������̌��l "  + t4.toPlainString());

		//�ō����z�̌v�Z��1.1���|����B
		//1000�~1.1=1100
		BigDecimal t5 = b1.multiply(b3);
		System.out.println( "1�������l "  + t5.toPlainString());


		//1��������0.9���|���������ʓI�Ȃ悤��
		//1000�~(1-0.1)
		BigDecimal t6 = b1.multiply(b4);
		System.out.println( "1�������l "  + t6.toPlainString());

		//�l�����O�̐��l�̌v�Z
		//1��������900�̌��l�̋��ߕ�
		//900�~(100��(100-10))
		BigDecimal t7 = b5.multiply( b6.divide( b8.subtract(b7), 10, RoundingMode.UP));
		System.out.println( "1�������O�l "  + t7.toPlainString());
	}
}