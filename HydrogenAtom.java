// 具体的な原子の実装（例：水素原子）
class HydrogenAtom extends Atom {
    public HydrogenAtom(float x, float y, float z, float charge) {
        // 水素の相対質量を便宜上 1.0 とします
        super(x, y, z, charge, 1.0f);
    }
}