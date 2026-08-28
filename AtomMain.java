// 実行用メインクラス
public class AtomMain {
    public static void main(String[] args) {
        // 座標(0,0,0) にプラスの電荷(1.0)を持つ原子A
        Atom atomA = new HydrogenAtom(0, 0, 0, 1.0f);
        // 座標(2,0,0) にマイナスの電荷(-1.0)を持つ原子B（異符号なので引き合うはず）
        Atom atomB = new HydrogenAtom(2, 0, 0, -1.0f);

        System.out.println("--- 異符号（引き合う力）のテスト ---");
        Electromagnetism.applyElectromagneticForce(atomA, atomB); // AがBから受ける力
        
        System.out.println("\n--- 同符号（反発する力）のテスト ---");
        Atom atomC = new HydrogenAtom(2, 0, 0, 1.0f); // 座標(2,0,0) にプラスの電荷(1.0)
        Electromagnetism.applyElectromagneticForce(atomA, atomC); // AがCから受ける力
    }
}