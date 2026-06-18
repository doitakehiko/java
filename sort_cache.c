#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// クイックソートの比較関数
int compare_int(const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

void run_experiment(size_t num_elements, const char* cache_target) {
    // 必要なメモリ容量を計算
    size_t size_bytes = num_elements * sizeof(int);
    
    // 動的メモリ確保
    int *array = (int*)malloc(size_bytes);
    if (array == NULL) {
        printf("メモリ確保に失敗しました。\n");
        return;
    }

    // 乱数で配列を初期化（この時点でキャッシュにロードされる）
    srand(42);
    for (size_t i = 0; i < num_elements; i++) {
        array[i] = rand();
    }

    // 時間計測開始
    clock_t start = clock();

    // C言語標準のクイックソートを実行
    qsort(array, num_elements, sizeof(int), compare_int);

    // 時間計測終了
    clock_t end = clock();
    double time_taken = ((double)(end - start)) / CLOCKS_PER_SEC;

    printf("【%s】\n", cache_target);
    printf("  要素数: %zu 万件\n", num_elements / 10000);
    printf("  データサイズ: %.2f MB\n", (double)size_bytes / (1024 * 1024));
    printf("  実行時間: %f 秒\n\n", time_taken);

    free(array);
}

int main() {
    printf("=== i7-3770 CPUキャッシュソート実験 (L3: 8MB) ===\n\n");

    // 1. L1/L2キャッシュに収まるレベル (約200KB)
    run_experiment(50000, "L1/L2キャッシュ内完結");

    // 2. L3キャッシュ内に完全に収まるレベル (約4MB) ← これが本番
    run_experiment(1000000, "L3キャッシュ内完結 (超高速)");

    // 3. L3キャッシュを大幅に超えてメインメモリ(RAM)を跨ぐレベル (約40MB)
    run_experiment(10000000, "L3超過・メインメモリ(RAM)アクセス発生");

    return 0;
}


