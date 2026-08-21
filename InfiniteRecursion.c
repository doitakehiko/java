#include <stdio.h>

void InfiniteRecursion(int count)
{
    // 何回呼び出されたかを表示（クラッシュ直前の数値をカウント）
    printf("呼び出し回数: %d\n", count);
    
    // 自分自身を無限に呼び出す
    InfiniteRecursion(count + 1);
}

int main(void)
{
    InfiniteRecursion(1);
    return 0;
}

