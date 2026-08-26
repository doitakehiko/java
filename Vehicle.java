public class Vehicle {
    private Engine engine;
    private double wearRate = 0.0; // 0.0 (新品) 〜 1.0 (大破)

    public Vehicle(Engine engine) {
        this.engine = engine;
    }

    // 時間経過（分単位など）で車全体が劣化する
    public void elapseTime(int minutes) {
        // 稼働時間に応じて損耗率を自動計算 (例: 1分で0.5%劣化)
        this.wearRate += minutes * 0.005;
        if (this.wearRate > 1.0) this.wearRate = 1.0;
        
        // エンジン側にも稼働時間を記録させる
        engine.addOperatingTime(minutes);
    }

    public void start() {
        // エンジン起動時は、このVehicle自身の損耗状態を参照させる
        if (engine.ignite(this)) {
            System.out.println("乗り物が発進しました。");
        } else {
            System.out.println("エンジンがかかりません。");
        }
    }

    public double getWearRate() {
        return wearRate;
    }
}
