// 2つの原子を繋ぐ「バネ（結合）」を表すクラス
class Bond {
    public Atom atom1;
    public Atom atom2;
    
    public float springConstant; // バネ定数 (k) : 大きいほど硬い結合になる
    public float restLength;     // 自然長 : 原子同士が保ちたい理想の距離

    public Bond(Atom atom1, Atom atom2, float springConstant, float restLength) {
        this.atom1 = atom1;
        this.atom2 = atom2;
        this.springConstant = springConstant;
        this.restLength = restLength;
    }

    // バネの力を計算して、両方の原子に適用する
    public void applySpringForce() {
        // 1. atom1 から atom2 への方向ベクトルと距離を計算
        float dx = atom2.position.x - atom1.position.x;
        float dy = atom2.position.y - atom1.position.y;
        float dz = atom2.position.z - atom1.position.z;
        
        Vector3 directionVec = new Vector3(dx, dy, dz);
        float currentDistance = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (currentDistance == 0) return;

        // 2. 理想の距離からの「ズレ（変位 x）」を計算
        float displacement = currentDistance - restLength;

        // 3. フックの法則 : F = k * x 
        // displacement がプラス（離れすぎ）なら引き合う力、マイナス（近すぎ）なら反発する力になる
        float forceMagnitude = springConstant * displacement;

        // 4. 力のベクトルを作成
        Vector3 forceVector = directionVec.normalized().multiply(forceMagnitude);

        // 作用反作用の法則：atom1 に引っ張る力を加え、atom2 には逆向きの力を加える
        atom1.addForce(forceVector);
        atom2.addForce(forceVector.multiply(-1.0f));
    }
}
