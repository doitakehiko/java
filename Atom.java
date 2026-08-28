// 3D空間に「実体」として存在する原子クラス
abstract class Atom {
    // 3D空間上のリアルタイムな現在座標
    public Vector3 position;
    // 原子が移動する速度ベクトル
    public Vector3 velocity;
    // このフレームで原子にかかっている合計の力
    public Vector3 accumulatedForce;

    public float charge; // 電荷
    public float mass;   // 質量（重い原子ほど力に対して動きにくくなる）

    public Atom(float x, float y, float z, float charge, float mass) {
        this.position = new Vector3(x, y, z);
        this.velocity = new Vector3(0, 0, 0);
        this.accumulatedForce = new Vector3(0, 0, 0);
        this.charge = charge;
        this.mass = (mass <= 0) ? 1.0f : mass; // 0除算防止
    }

    // 外部から電磁気力などの「力」を蓄積する
    public void addForce(Vector3 force) {
        this.accumulatedForce = this.accumulatedForce.add(force);
    }

    // ゲームの1フレーム（デルタタイム）ごとに座標を物理更新するメソッド
    public void updatePosition(float deltaTime) {
        // 1. 加速度を計算 (a = F / m)
        Vector3 acceleration = this.accumulatedForce.multiply(1.0f / this.mass);

        // 2. 速度を更新 (v = v + a * t)
        this.velocity = this.velocity.add(acceleration.multiply(deltaTime));

        // 3. 座標を更新 (p = p + v * t)
        this.position = this.position.add(this.velocity.multiply(deltaTime));

        // 4. 力の蓄積をリセット（次のフレームの計算のため）
        this.accumulatedForce.reset();
    }
}