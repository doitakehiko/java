public class Engine {
    private boolean _engineRunning = false;


    // 引数で車の損耗率（0.0 〜 1.0）を受け取るようにする
    public boolean ignite(double wearRate) {
        _engineRunning = true;
        return _engineRunning;
    }
    // ゲッター
    public boolean isEngineRunning() {
        return _engineRunning;
    }
}
