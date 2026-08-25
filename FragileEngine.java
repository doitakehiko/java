import java.util.Random;

public class FragileEngine extends Engine {
    private final Random random = new Random();

    @Override
    public boolean ignite(double wearRate) {
        // 例: 損耗率が 0.4(40%) のとき、40%の確率で失敗する（乱数が0.4未満なら失敗）
        if (random.nextDouble() < wearRate) {
            System.out.println("（損耗率 " + (int)(wearRate * 100) + "%）プスン... 起動失敗。");
            return false; 
        }

        System.out.println("（損耗率 " + (int)(wearRate * 100) + "%）ドルン！ 起動成功！");
        return super.ignite(wearRate); // 成功したら親クラスの処理で起動状態にする
    }
}