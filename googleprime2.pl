use strict;
use warnings;

# eの文字列（100番目の要素に 7427466391 が含まれています）
my $e = "2718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059";

# 素数判定関数（通常の数値計算にするだけで劇的に高速化します）
sub is_prime {
    my $n = shift;
    return 0 if $n <= 1;
    return 1 if $n == 2;
    return 0 if $n % 2 == 0;
    
    # √n まで奇数でループ
    my $sqrt = int(sqrt($n));
    for (my $i = 3; $i <= $sqrt; $i += 2) {
        return 0 if $n % $i == 0;
    }
    return 1;
}

# 10桁ずつスライドしながらチェック
for (my $i = 0; $i <= length($e) - 10; $i++) {
    my $num = substr($e, $i, 10);
    
    # ⚠️ 現代の64bit Perlなら、このまま普通の数値として扱ってOKです！
    if (is_prime($num)) {
        print "発見した素数 (Perl): $num\n";
        last; # 最初の1つが見つかったら終了
    }
}

