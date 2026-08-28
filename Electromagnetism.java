class Electromagnetism {
    private static final float K = 100.0f; 

    public static void applyElectromagneticForce(Atom atom1, Atom atom2) {
        float dx = atom2.x - atom1.x;
        float dy = atom2.y - atom1.y;
        float dz = atom2.z - atom1.z;
        
        Vector3 directionVec = new Vector3(dx, dy, dz);

        // 距離の2乗を計算
        float distanceSq = (dx * dx + dy * dy + dz * dz);
        
        // 【修正】完全に同じ座標（距離0）の時だけゼロ除算を防ぐ
        if (distanceSq == 0) return;
        
        // F = K * (q1 * q2) / r^2
        float forceMagnitude = K * (atom1.charge * atom2.charge) / distanceSq;

        // 【修正】力の向きを計算（きれいな 0 にするために符号の扱いを整理）
        // 異符号（マイナス）なら引き合う（+directionVec）、同符号（プラス）なら反発（-directionVec）
        float directionSign = (forceMagnitude < 0) ? 1.0f : -1.0f;
        float absoluteMagnitude = Math.abs(forceMagnitude);

        Vector3 forceVector = directionVec.normalized().multiply(absoluteMagnitude * directionSign);

        // 【修正】「-0.0」の表示を防ぐためのクリーンアップ
        if (forceVector.x == 0.0f) forceVector.x = 0.0f;
        if (forceVector.y == 0.0f) forceVector.y = 0.0f;
        if (forceVector.z == 0.0f) forceVector.z = 0.0f;

        atom1.addForce(forceVector);
    }
}
