public class SetIntersection {
    public static void main(String[] args) {
	int[] numSetA = {1, 2, 3, 4, 5, 6};
	int[] numSetB = {3, 4, 5, 6, 7, 8};

	for ( int i = 0; i < numSetA.length; i++ ) {
		for ( int n = 0; n < numSetB.length; n++ ) {
			if ( numSetA[i] == numSetB[n] ) {
				System.out.println( numSetA[i] );
			}
		}
	}

    }
}