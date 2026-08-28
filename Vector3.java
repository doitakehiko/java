// 3次元ベクトルクラス（足し算などを追加してゲーム向けに強化）
class Vector3 {
    public float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x; this.y = y; this.z = z;
    }

    public Vector3 add(Vector3 v) {
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector3 normalized() {
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
        if (magnitude > 0) return new Vector3(x / magnitude, y / magnitude, z / magnitude);
        return new Vector3(0, 0, 0);
    }

    public Vector3 multiply(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public void reset() {
        this.x = 0; this.y = 0; this.z = 0;
    }
}