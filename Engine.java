public class Engine {
    private boolean _engineRunning = false;
    private int operatingTime = 0; // 稼働時間

    // 稼働時間を蓄積する
    public void addOperatingTime(int minutes) {
        this.operatingTime += minutes;
    }

    // 引数はVehicle全体。通常のエンジンは損耗率を無視して100%起動
    public boolean ignite(Vehicle vehicle) {
        _engineRunning = true;
        return _engineRunning;
    }

    public int getOperatingTime() {
        return operatingTime;
    }
}
