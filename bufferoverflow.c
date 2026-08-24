#include <stdio.h>

int main() {
    int secret_code = 12345; // 破壊される側の重要な変数
    char array[4];           // 4バイト（4文字）しか入らない箱

    // arrayは4つ（0〜3）しか部屋がないのに、
    // ポインタの計算を間違えて「5番目（インデックス4）」の部屋に書き込む
    array[4] = 99; // ❌ 本来はアクセスしてはいけない隣のメモリ

    // 結果を表示
    printf("secret_codeの中身: %d\n", secret_code);
    
    // 環境によっては、secret_codeの値が「99」に書き換わってしまいます！
    return 0;
}

