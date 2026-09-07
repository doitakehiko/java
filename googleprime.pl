use strict;
use warnings;
use Math::BigInt; # ⚠️ 10桁の型あふれ（丸め誤差）を防ぐために必須！

# eの文字列（実際は数千桁）
my $e = "2718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059";

# 素数判定関数
sub is_prime {
    my $n = Math::BigInt->new(shift);
    return 0 if $n <= 1;
    return 1 if $n == 2;
    return 0 if $n % 2 == 0;
    
    # √n まで奇数でループ
    my $sqrt = $n->bsqrt();
    for (my $i = Math::BigInt->new(3); $i <= $sqrt; $i += 2) {
        return 0 if $n % $i == 0;
    }
    return 1;
}

# 10桁ずつスライドしながらチェック
for (my $i = 0; $i <= length($e) - 10; $i++) {
    my $num = substr($e, $i, 10);
    if (is_prime($num)) {
        print "発見した素数 (Perl): $num\n";
        last; # 最初の1つが見つかったら終了
    }
}

