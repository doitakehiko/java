#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int DeliberatelyLeak()
{
    size_t num_elements = (size_t)pow(sizeof(unsigned long long), sizeof(unsigned long long));
    unsigned long long *ptr = (unsigned long long*)malloc(sizeof(unsigned long long) * num_elements);

    if (ptr == NULL) {
        // メモリが足りなくて確保できなかった場合
        printf("【警告】mallocがNULLを返しました（メモリ満杯）\n");
        return -1;
    }

    // ★重要：確保したメモリの先頭と末尾に実際にデータを書き込んで、OSにメモリを強制消費させる
    ptr[0] = 12345;
    ptr[num_elements - 1] = 67890;

    printf("メモリ確保＆書き込み成功: %p\n", (void*)ptr);
    return 0;
}

int main(void)
{
    for( ; ; )
    {
        DeliberatelyLeak();
    }
    return 0;
}

