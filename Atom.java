// 原子（実体）クラス
class Atom {
    public String id;
    public Vector3 position;
    public Vector3 velocity;
    public Vector3 accumulatedForce;
    public float charge;
    public float mass;

    public Atom(String id, float x, float y, float z, float charge, float mass) {
        this.id = id;
        this.position = new Vector3(x, y, z);
        this.velocity = new Vector3(0, 0, 0);
        this.accumulatedForce = new Vector3(0, 0, 0);
        this.charge = charge;
        this.mass = (mass <= 0) ? 1.0f : mass;
    }

    public void addForce(Vector3 force) {
        this.accumulatedForce = this.accumulatedForce.add(force);
    }

    // 物理演算による位置更新
    public void updatePosition(float deltaTime) {
        Vector3 acceleration = this.accumulatedForce.multiply(1.0f / this.mass);
        this.velocity = this.velocity.add(acceleration.multiply(deltaTime));
        this.position = this.position.add(this.velocity.multiply(deltaTime));
        this.accumulatedForce.reset(); // フレームの最後に力をリセット
    }
}
