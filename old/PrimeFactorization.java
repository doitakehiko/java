import java.util.ArrayList;
import java.util.List;
public class PrimeFactorization {
	public static List<Integer> primeFactorization(int num) {
		int[] primeList;
		primeList = new int[6];
		primeList[0] = 2;
		primeList[1] = 3;
		primeList[2] = 5;
		primeList[3] = 7;
		primeList[4] = 11;
		primeList[5] = 13;
		List<Integer> factors = new ArrayList<>();
		int primeNum = 0;
		if (num <= 1) {
			return factors;
		}
		for (int i = 0; i < primeList.length ; i++) {
			primeNum = primeList[i];
			if (num % primeNum == 0) {
				factors.add(primeNum);
			}
		}
		return factors;
	}


	public static void main(String[] args) {
	        int number = 143;
		List<Integer> primeFactors = primeFactorization(number);
		System.out.print(number + " = ");
		 for (int i = 0; i < primeFactors.size(); i++) {
			System.out.print(primeFactors.get(i));
			if (i < primeFactors.size() - 1) {
				System.out.print(" * ");
			}

		}
		System.out.println();
	}
}
