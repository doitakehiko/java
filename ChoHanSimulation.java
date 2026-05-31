import java.util.Random;

public class ChoHanSimulation {
	public static void main(String[] args) {
		int simulations = 10000;     // シミュレーション回数（1万回）
		int initialCapital = 10000;  // 軍資金（1万円）
		int baseBet = 100;           // 最初の賭け金（100円）
		int targetProfit = 1000;     // 目標利益（プラス1,000円で勝ち逃げ）

		int successCount = 0;
		int bankruptcyCount = 0;
		Random random = new Random();

		for (int i = 0; i < simulations; i++) {
			int capital = initialCapital;
			int currentBet = baseBet;

			// 軍資金がある、かつ目標金額に達していない間ループ
			while (capital > 0 && capital < (initialCapital + targetProfit)) {
				// 手持ち資金より賭け金が大きくなってしまった場合
				if (capital < currentBet) {
					currentBet = capital; // 全財産を賭ける（破滅へのカウントダウン）
				}

				// 丁半博打（50%の確率で勝敗を決める）
				boolean isWin = random.nextBoolean();

				if (isWin) {
					capital += currentBet;
					currentBet = baseBet; // 勝ったら賭け金を最初（100円）に戻す
				} else {
					capital -= currentBet;
					currentBet *= 2;      // 負けたら倍プッシュ（2倍）
				}
			}

			// 結果の集計
			if (capital <= 0) {
				bankruptcyCount++;
			} else {
				successCount++;
			}
		}

		// 結果発表
		System.out.println("=== シミュレーション結果 ===");
		System.out.println("試行回数: " + simulations + " 回");
		System.out.println("勝ち逃げ成功（+1,000円達成）: " + successCount + " 回");
		System.out.println("大破産（資金ゼロ）: " + bankruptcyCount + " 回");
		System.out.println("破産確率: " + String.format("%.2f", ((double)bankruptcyCount / simulations) * 100) + " %");
	}
}
