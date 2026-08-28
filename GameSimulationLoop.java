import java.util.Random;
import java.util.List;
import java.util.ArrayList;
// メインループ（ゲーム環境の模倣）
public class GameSimulationLoop {
    public static void main(String[] args) {
        List<Atom> atomList = new ArrayList<>();
        Random random = new Random();

        // 1. 多体シミュレーションの準備：10個の原子をランダムに配置
        // プラス電荷とマイナス電荷を交互に生成して引き合うようにする
        for (int i = 0; i < 10; i++) {
            float rx = (random.nextFloat() * 10.0f) - 5.0f; // -5.0 〜 5.0 の範囲
            float ry = (random.nextFloat() * 10.0f) - 5.0f;
            float rz = (random.nextFloat() * 10.0f) - 5.0f;
            float charge = (i % 2 == 0) ? 1.0f : -1.0f;
            
            atomList.add(new Atom("Atom_" + i, rx, ry, rz, charge, 1.0f));
        }

        // 2. ゲームループの模倣（1秒間に60回更新する環境を想定、deltaTimeは一律約0.016秒）
        float deltaTime = 0.016f; 
        int totalFrames = 100; // 100フレーム分シミュレート

        System.out.println("====== 3D多体電磁気力シミュレーション開始 ======");

        for (int frame = 1; frame <= totalFrames; frame++) {
            // ステップ A: すべてのペア間で電磁気力を計算（多体ループ構造）
            // 二重ループにより、重複なく全原子の組み合わせ（n*(n-1)/2 通り）に力を適用
            for (int i = 0; i < atomList.size(); i++) {
                for (int j = i + 1; j < atomList.size(); j++) {
                    Electromagnetism.applyForceBetween(atomList.get(i), atomList.get(j));
                }
            }

            // ステップ B: 蓄積された力をもとに全員の位置を更新
            for (Atom atom : atomList) {
                atom.updatePosition(deltaTime);
            }

            // ステップ C: 特定のフレームでログを出力して動きを観察
            if (frame == 1 || frame == 50 || frame == 100) {
                System.out.println("\n--- フレーム " + frame + " の状態 ---");
                for (int i = 0; i < 3; i++) { // スペースの都合上、先頭3つのみ表示
                    Atom a = atomList.get(i);
                    System.out.printf("%s [電荷:%.1f] 位置:%s 速度:%s%n", 
                        a.id, a.charge, a.position.toString(), a.velocity.toString());
                }
                
                // 衝突（近接）の検知ロジックのデモ（Atom_0 と Atom_1 の距離を監視）
                Atom a0 = atomList.get(0);
                Atom a1 = atomList.get(1);
                float dx = a1.position.x - a0.position.x;
                float dy = a1.position.y - a0.position.y;
                float dz = a1.position.z - a0.position.z;
                double currentDist = Math.sqrt(dx*dx + dy*dy + dz*dz);
                System.out.printf(">> [注目エリア] Atom_0 と Atom_1 の現在の距離: %.3f%n", currentDist);
                if (currentDist < 0.5f) {
                    System.out.println("   ★引き合っていた2つの原子が衝突条件を満たしました！");
                }
            }
        }
        System.out.println("\n====== シミュレーション終了 ======");
    }
}