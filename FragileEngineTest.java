public class FragileEngineTest extends Engine {
	public static void main(String[] args) {
		FragileEngine fragileEngine = new FragileEngine();
		double nfailures = 0;
		final double LIMIT = 100;
		for( int i = 0; i < (int)LIMIT; i++ ) {
			if( fragileEngine.ignite(0.4) == false ) {
				nfailures++;
				System.out.println("エンジン起動失敗:回数" + nfailures);
			}
		}
		double rate = (nfailures / LIMIT) * 100.0;
		System.out.println("失敗パーセント:" + rate );
	}
}