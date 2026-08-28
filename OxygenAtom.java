// --- これまで作成した物理ベースのクラス（動作確認用に一部具体化） ---
class OxygenAtom extends Atom {
    public OxygenAtom(float x, float y, float z) {
        super("O", x, y, z, -0.8f, 16.0f); // 酸素：マイナス電気、質量16
    }
}