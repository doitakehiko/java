#include <stdio.h>

int main(void)
{
    // 約16MBの巨大な配列をローカル変数として宣言（スタック領域に配置）
    // 16,000,000バイト ＞ 一般的なスタック上限（約8MB）
    char huge_array[16000000];

    // 配列の先頭にダミーの書き込みをして使用を確定させる
    huge_array[0] = 'A';

    printf("この文字は表示されずにクラッシュします: %c\n", huge_array[0]);
    return 0;
}

