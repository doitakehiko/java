public class ArraySumTest {
	public static void main (String[] args) {
		ArraySum arraysum = new ArraySum();
		int result = 0;
		int[] num;
		num = new int[args.length];
		for( int i = 0; i < args.length; i++ ) {
			num[i] = Integer.parseInt(args[i]);
		}
		result = arraysum.calculateSum(num);
		System.out.println( result );
	}
}