import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 3次元ベクトルクラス
class Vector3 {
    public float x, y, z;
    public Vector3(float x, float y, float z) { this.x = x; this.y = y; this.z = z; }
    public Vector3 add(Vector3 v) { return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z); }
    public Vector3 multiply(float scalar) { return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar); }
    public void reset() { this.x = 0; this.y = 0; this.z = 0; }
    public Vector3 normalized() {
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
        if (magnitude > 0) return new Vector3(x / magnitude, y / magnitude, z / magnitude);
        return new Vector3(0, 0, 0);
    }
    @Override
    public String toString() { return String.format("(%.2f, %.2f, %.2f)", x, y, z); }
}

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
        this.mass = mass;
    }

    public void addForce(Vector3 force) { this.accumulatedForce = this.accumulatedForce.add(force); }

    public void updatePosition(float deltaTime) {
        // 加速度 (a = F / m) から速度と位置を更新
        Vector3 acceleration = this.accumulatedForce.multiply(1.0f / this.mass);
        this.velocity = this.velocity.add(acceleration.multiply(deltaTime));
        
        // 【ダンパー効果】ゲーム用に速度を少し減衰させ、ブルブル震え続けるのを防ぐ（ネバネバ感の強化）
        this.velocity = this.velocity.multiply(0.95f); 
        
        this.position = this.position.add(this.velocity.multiply(deltaTime));
        this.accumulatedForce.reset();
    }
}