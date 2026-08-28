// --- メインの実装 ---
public class MoleculeBuilderDemo {
    public static void main(String[] args) {
        System.out.println("====== 水分子（H2O）の構築開始 ======");

        // 1. 水分子という「器」を作成
        Molecule water = new Molecule("Water_Molecule_1");

        // 2. 3D空間に原子を実体化
        // 中心に酸素、左右斜めに水素を配置（現実の水分子の104.5度に近い配置のイメージ）
        Atom oxygen = new OxygenAtom(0.0f, 0.0f, 0.0f);
        Atom hydrogen1 = new HydrogenAtom(-0.96f, 0.75f, 0.0f);
        Atom hydrogen2 = new HydrogenAtom(0.96f, 0.75f, 0.0f);

        // 器（分子クラス）に原子を登録
        water.addAtom(oxygen);
        water.addAtom(hydrogen1);
        water.addAtom(hydrogen2);

        // 3. 酸素とそれぞれの水素を「バネ」で結合する
        // 硬さ（springConstant）= 300.0f, 理想の距離（restLength）= 1.2f （球体同士が程よく離れる距離）
        water.createBond(oxygen, hydrogen1, 300.0f, 1.2f);
        water.createBond(oxygen, hydrogen2, 300.0f, 1.2f);

        System.out.println("水分子が正常に組み立てられました。");
        System.out.println("構成原子数: " + water.atoms.size() + "個");
        System.out.println("内部結合数: " + water.bonds.size() + "本");

        // 4. ゲームループでのシミュレーションテスト（最初の3フレーム）
        System.out.println("\n--- 物理シミュレーションテスト（形状維持の挙動） ---");
        float deltaTime = 0.016f;

        for (int frame = 1; frame <= 3; frame++) {
            System.out.println("[Frame " + frame + "]");
            
            // ステップA: 分子内部のバネの力を計算（形をキープする）
            water.applyInternalForces();

            // ステップB: 位置を更新して力をリセット
            for (Atom atom : water.atoms) {
                atom.updatePosition(deltaTime);
                System.out.printf("  %s の現在位置: (%.3f, %.3f, %.3f)%n", 
                    atom.id, atom.position.x, atom.position.y, atom.position.z);
            }
        }
    }
}