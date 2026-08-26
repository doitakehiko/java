import java.util.Random;

public class FragileEngine extends Engine {
    private final Random random = new Random();

    @Override
    public boolean ignite(Vehicle vehicle) {
        // 引数から受け取ったVehicleの損耗率（上層で自動計算されたもの）を取得
        double currentWear = vehicle.getWearRate();

        // 損耗率に基づいた確率判定
        if (random.nextDouble() < currentWear) {
            System.out.printf("[警告] 損耗率%.1f%% のためエンジン起動に失敗しました。\n", currentWear * 100);
            return false;
        }

        System.out.printf("[正常] 損耗率%.1f%% をクリア。エンジンを起動します。\n", currentWear * 100);
        return super.ignite(vehicle);
    }
}
