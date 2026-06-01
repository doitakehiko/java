import java.util.Random;

public class GamblingSimulation {
	public static void main(String[] args) {
		int rounds = 10000;              // 総ゲーム回数（1万回）
		double houseEdgeRate = 0.05;     // 寺銭（5%）

		// プレイヤーの初期設定
		double[] capital = new double[10];
		double[] betAmount = new double[10];
		boolean[] betOnCho = new boolean[10]; // true: 丁, false: 半

		for (int i = 0; i < 10; i++) {
			capital[i] = 10000.0;        // 全員一律1万円からスタート
		}

		double p10BaseBet = 100.0;       // 倍プッシュ（プレイヤー10）の初期賭け金
		double p10CurrentBet = p10BaseBet;

		Random random = new Random();

		System.out.println("=== シミュレーション開始 ===");

		for (int r = 1; r <= rounds; r++) {
		// 1. 各プレイヤーの賭け金と、丁半どちらに賭けるかを決定
		double totalChoBet = 0;
		double totalHanBet = 0;

		for (int i = 0; i < 10; i++) {
			// 資金がゼロのプレイヤーはパス
			if (capital[i] <= 0) {
				betAmount[i] = 0;
				continue;
			}

			// 賭け金の決定
			if (i < 9) {
				betAmount[i] = 100.0; // プレイヤー1〜9は常に100円
			} else {
				betAmount[i] = p10CurrentBet; // プレイヤー10は倍プッシュ
			}

			// 持ち金以上のベットはできない（全賭けに制限）
			if (betAmount[i] > capital[i]) {
				betAmount[i] = capital[i];
			}

			// どちらに賭けるかはランダム（10人それぞれが独自に選択）
			betOnCho[i] = random.nextBoolean();

			if (betOnCho[i]) {
				totalChoBet += betAmount[i];
			} else {
				totalHanBet += betAmount[i];
			}
		}

		// 全員の賭け金の総額（このゲームの総プール金）
		double totalPool = totalChoBet + totalHanBet;
		if (totalPool == 0) break; // 全員破産したら終了

		// 2. 丁半の勝敗決定（50%の確率）
		boolean isChoWin = random.nextBoolean();

		// 3. 配当の計算と分配
		double winnerTotalBet = isChoWin ? totalChoBet : totalHanBet;
		double loserTotalBet = isChoWin ? totalHanBet : totalChoBet;

		// プレイヤー10（倍プッシュ）が勝ったかどうかのフラグ
		boolean p10Won = false;

		for (int i = 0; i < 10; i++) {
			if (betAmount[i] == 0) continue;

				boolean isPlayerWinner = (betOnCho[i] == isChoWin);

				if (isPlayerWinner) {
				// 勝った場合：自分の賭け金が戻り、さらに敗者の総賭け金から「自分のベット割合」と「寺銭5%引後」の利益を得る
				double myBetRatio = betAmount[i] / winnerTotalBet;
				double myProfit = loserTotalBet * myBetRatio * (1.0 - houseEdgeRate);

				capital[i] += myProfit;

			if (i == 9) p10Won = true;
			} else {
				// 負けた場合：賭け金を失う
				capital[i] -= betAmount[i];
			}
		}

		// 4. 倍プッシュプレイヤー（プレイヤー10）の次回賭け金更新
		if (capital[9] > 0) {
			if (p10Won) {
				p10CurrentBet = p10BaseBet; // 勝ったら初期値に戻す
			} else {
				p10CurrentBet *= 2;        // 負けたら倍プッシュ
			}
		} else {
			p10CurrentBet = 0; // 破産
		}

		// 途中経過の表示（1000回ごと）
		if (r % 2000 == 0 || r == 1) {
				System.out.printf("[ゲーム %4d回終了時] プレイヤー1〜9の平均資金: %.0f円 | 倍プッシュ(P10)の資金: %.0f円\n", r, getAverageCapital(capital, 0, 8), capital[9]);
		}
	}

	// 最終結果発表
	System.out.println("\n=== 1万回プレイ後の最終結果 ===");
	for (int i = 0; i < 9; i++) {
		System.out.printf("プレイヤー%d (定額ベット): %.0f 円\n", (i + 1), capital[i]);
	}
		System.out.printf("プレイヤー10 (倍プッシュ): %.0f 円\n", capital[9]);
	}

	private static double getAverageCapital(double[] capital, int start, int end) {
		double sum = 0;
		for (int i = start; i <= end; i++) {
			sum += capital[i];
		}
		return sum / (end - start + 1);
	}
}
