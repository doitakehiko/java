#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

// 素数判定関数（高速な試し割り）
int is_prime(unsigned long long n) {
    if (n <= 1) return 0;
    if (n == 2) return 1;
    if (n % 2 == 0) return 0;
    
    unsigned long long limit = (unsigned long long)sqrt((double)n);
    for (unsigned long long i = 3; i <= limit; i += 2) {
        if (n % i == 0) return 0;
    }
    return 1;
}

int main() {
    // eの文字列（実際は数千桁）
    const char *e = "2718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059";
    int len = strlen(e);
    char buf[11]; // 10桁 + ヌル終端

    // 10桁ずつ切り出して判定
    for (int i = 0; i <= len - 10; i++) {
        strncpy(buf, e + i, 10);
        buf[10] = '\0';
        
        // 文字列を64bit整数に変換（32bitのatoiを使うと壊れるため strtoull を使用）
        unsigned long long num = strtoull(buf, NULL, 10);
        
        if (is_prime(num)) {
            printf("発見した素数 (C言語): %llu\n", num);
            break; // 最初の1つで終了
        }
    }
    return 0;
}
//gcc -std=c99 googleprime.c -o googleprime -lm

