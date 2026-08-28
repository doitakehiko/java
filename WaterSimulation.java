// ==========================================
// 4. メインループ（マウス操作とネバネバ連動のシミュレーション）
// ==========================================
import java.util.ArrayList;
import java.util.List;
public class WaterSimulation {
    
    // 水分子を1つ組み立てるヘルパーメソッド
    private static Molecule createWater(String name, float offsetX, float offsetY, float offsetZ) {
        Molecule water = new Molecule(name);
        Atom o = new Atom(name + "_O", 0.0f + offsetX, 0.0f + offsetY, 0.0f + offsetZ, -0.8f, 16.0f);
        Atom h1 = new Atom(name + "_H1", -0.9f + offsetX, 0.6f + offsetY, 0.0f + offsetZ, 0.4f, 1.0f);
        Atom h2 = new Atom(name + "_H2", 0.9f + offsetX, 0.6f + offsetY, 0.0f + offsetZ, 0.4f, 1.0f);
        water.atoms.add(o); water.atoms.add(h1); water.atoms.add(h2);
        water.bonds.add(new Bond(o, h1, 500.0f, 1.1f)); // 内部は硬いバネ
        water.bonds.add(new Bond(o, h2, 500.0f, 1.1f));
        return water;
    }

    public static void main(String[] args) {
        List<Molecule> worldMolecules = new ArrayList<>();

        // 水分子A（操作用）と、少し離れた位置にある水分子B（追従用）を生成
        Molecule waterA = createWater("Water_A", 0.0f, 0.0f, 0.0f);
        //Molecule waterB = createWater("Water_B", 2.5f, 0.0f, 0.0f); // 右側に配置
	// 水分子Bをもう少し近く（2.5 ではなく 1.8）に配置して結合を強くする
	Molecule waterB = createWater("Water_B", 1.8f, 0.0f, 0.0f);
        
        worldMolecules.add(waterA);
        worldMolecules.add(waterB);

        // マウスで「水分子A」を掴んだ状態にする
        waterA.isGrabbed = true;

        System.out.println("====== 水分子のネバネバ連動シミュレーション開始 ======");
        System.out.println("初期状態: 水分子A(0,0,0) を掴み、水分子B は(2.5, 0, 0)に浮いています。");

        float deltaTime = 0.016f; // 60FPSを想定

        // 60フレーム（約1秒間）のループ
        for (int frame = 1; frame <= 60; frame++) {
            
            // 【マウス操作の模倣】フレーム10から、掴んでいる水分子Aをゆっくり「上(+Y方向)」に引っ張る
            /*if (frame >= 10) {
                waterA.translate(new Vector3(0.0f, 0.1f, 0.0f)); 
            }*/
	    // マウスの移動速度を 0.1 から 0.03 に落とし、Bがついてこれるようにする
            if (frame >= 10) {
                waterA.translate(new Vector3(0.0f, 0.03f, 0.0f)); 
            }

            // --- 物理演算フェーズ ---
            
            // 1. 分子【内】の形状維持（バネ）
            for (Molecule mol : worldMolecules) {
                mol.applyInternalForces();
            }

            // 2. 分子【間】の多体ループ（水素結合・電磁気力）
            // 全ての分子の組み合わせを2重ループで処理
            for (int i = 0; i < worldMolecules.size(); i++) {
                for (int j = i + 1; j < worldMolecules.size(); j++) {
                    InterMolecularPhysics.applyElectromagneticForce(worldMolecules.get(i), worldMolecules.get(j));
                }
            }

            // 3. 全原子の座標更新
            for (Molecule mol : worldMolecules) {
                mol.updateAtoms(deltaTime);
            }

            // --- ログ出力（動きの観察） ---
            if (frame == 1 || frame == 30 || frame == 60) {
                System.out.printf("%n[フレーム %d] ------------------------%n", frame);
                if (frame >= 10) System.out.println("（★マウスでWater_Aを上に引っ張っています）");
                System.out.println("Water_A (酸素) の位置: " + waterA.atoms.get(0).position);
                System.out.println("Water_B (酸素) の位置: " + waterB.atoms.get(0).position);
                System.out.println("Water_B (酸素) の速度: " + waterB.atoms.get(0).velocity);
            }
        }
        System.out.println("%n====== シミュレーション終了 ======");
    }
}