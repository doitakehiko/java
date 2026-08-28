import java.util.ArrayList;
import java.util.List;

public class Molecule {
    public String name;             // 分子名（例: "Water"）
    public List<Atom> atoms;       // 分子を構成する原子のリスト
    public List<Bond> bonds;       // 分子内部のバネ（結合）のリスト

    public Molecule(String name) {
        this.name = name;
        this.atoms = new ArrayList<>();
        this.bonds = new ArrayList<>();
    }

    // 分子の中に原子を追加する
    public void addAtom(Atom atom) {
        this.atoms.add(atom);
    }

    // 分子の中の原子同士をバネで繋ぐ
    public void createBond(Atom a1, Atom a2, float springConstant, float restLength) {
        Bond bond = new Bond(a1, a2, springConstant, restLength);
        this.bonds.add(bond);
    }

    // 分子全体の形を維持するためのバネ計算を一括で実行
    public void applyInternalForces() {
        for (Bond bond : bonds) {
            bond.applySpringForce();
        }
    }
}
