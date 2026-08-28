// 具体的な原子の実装（例：水素原子）
class HydrogenAtom extends Atom {
    public HydrogenAtom(float x, float y, float z) {
        super("H", x, y, z, 0.4f, 1.0f);  // 水素：プラス電気、質量1
    }
}
