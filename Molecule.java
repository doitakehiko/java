import java.util.ArrayList;
import java.util.List;

// ==========================================
// 2. 分子クラス（コンテナ構造）
// ==========================================
class Molecule {
    public String name;
    public List<Atom> atoms = new ArrayList<>();
    public List<Bond> bonds = new ArrayList<>();
    public boolean isGrabbed = false; // マウスで掴まれているかどうかのフラグ

    public Molecule(String name) { this.name = name; }

    public void applyInternalForces() {
        for (Bond bond : bonds) { bond.applySpringForce(); }
    }

    public void updateAtoms(float deltaTime) {
        // マウスで掴まれている分子は、物理演算による位置更新をスキップする
        if (isGrabbed) {
            // 掴まれている間は速度を0にして、力が蓄積するのを防ぐ
            for (Atom atom : atoms) {
                atom.velocity.reset();
                atom.accumulatedForce.reset();
            }
            return;
        }
        for (Atom atom : atoms) { atom.updatePosition(deltaTime); }
    }

    // 分子全体をまとめて移動させる（マウス操作のシミュレート用）
    public void translate(Vector3 offset) {
        for (Atom atom : atoms) {
            atom.position = atom.position.add(offset);
        }
    }
}