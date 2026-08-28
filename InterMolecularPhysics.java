// ==========================================
// 3. 多体電磁気力（水素結合）の計算クラス
// ==========================================
class InterMolecularPhysics {
    private static final float COULOMB_K = 800.0f; // 分子同士を引き合わせる擬似クーロン定数

    // 異なる2つの分子の「すべての原子の組み合わせ」の間で働く電磁気力を計算する
    public static void applyElectromagneticForce(Molecule mol1, Molecule mol2) {
        for (Atom a1 : mol1.atoms) {
            for (Atom a2 : mol2.atoms) {
                float dx = a2.position.x - a1.position.x;
                float dy = a2.position.y - a1.position.y;
                float dz = a2.position.z - a1.position.z;
                float distanceSq = dx*dx + dy*dy + dz*dz;

                // 近づきすぎた時の無限大バグを防ぐ（近近反発クランプ）
                if (distanceSq < 0.4f) distanceSq = 0.4f;

                // F = K * (q1 * q2) / r^2
                float forceMagnitude = COULOMB_K * (a1.charge * a2.charge) / distanceSq;
                Vector3 directionVec = new Vector3(dx, dy, dz);
                float sign = (forceMagnitude < 0) ? 1.0f : -1.0f;
                Vector3 forceVector = directionVec.normalized().multiply(Math.abs(forceMagnitude) * sign);

                // 両方の原子に力を蓄積
                a1.addForce(forceVector);
                a2.addForce(forceVector.multiply(-1.0f));
            }
        }
    }
}