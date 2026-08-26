public class FragileEngineTest {
    public static void main(String[] args) {
        // 壊れやすいエンジンを積んだ乗り物を検証
        Engine fragileEngine = new FragileEngine();
        Vehicle myCar = new Vehicle(fragileEngine);

        System.out.println("--- 1回目：新品状態での起動 ---");
        myCar.start(); 

        System.out.println("\n--- 120分間ドライブ（経年劣化させる） ---");
        myCar.elapseTime(120); // ここで内部の損耗率が自動計算される（120 * 0.005 = 0.6）

        System.out.println("\n--- 2回目：劣化状態での起動テスト ---");
        myCar.start(); // 30%の確率で失敗するようになる
    }
}
