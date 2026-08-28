// 電磁気力計算クラス
class Electromagnetism {
    private static final float K = 50.0f; // 扱いやすい数値にした擬似クーロン定数

    public static void applyForceBetween(Atom atom1, Atom atom2) {
        float dx = atom2.position.x - atom1.position.x;
        float dy = atom2.position.y - atom1.position.y;
        float dz = atom2.position.z - atom1.position.z;

        float distanceSq = (dx * dx + dy * dy + dz * dz);
        
        // 衝突、または重なりによる無限大の力を防ぐ（近接クランプ）
        if (distanceSq < 0.1f) distanceSq = 0.1f;

        // F = K * (q1 * q2) / r^2
        float forceMagnitude = K * (atom1.charge * atom2.charge) / distanceSq;

        Vector3 directionVec = new Vector3(dx, dy, dz);
        
        // 異符号（マイナス）なら引き合う、同符号（プラス）なら反発
        float directionSign = (forceMagnitude < 0) ? 1.0f : -1.0f;
        float absoluteMagnitude = Math.abs(forceMagnitude);
        Vector3 forceVector = directionVec.normalized().multiply(absoluteMagnitude * directionSign);

        // お互いに作用反作用の力を加える
        atom1.addForce(forceVector);
        atom2.addForce(forceVector.multiply(-1.0f)); // 逆向きの力
    }
}