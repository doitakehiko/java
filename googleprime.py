import math

# eの文字列（実際は数千桁）
e_str = "2718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059"

# 素数判定関数
def is_prime(n):
    if n <= 1: return False
    if n == 2: return True
    if n % 2 == 0: return False
    # √n までループ（Pythonは自動で長整数を扱えるため型の罠がない ✨）
    for i in range(3, int(math.sqrt(n)) + 1, 2):
        if n % i == 0: return False
    return True

# 10桁ずつスライド（スライス機能が超強力）
for i in range(len(e_str) - 9):
    num = int(e_str[i:i+10])
    if is_prime(num):
        print ("発見した素数 (Python):", num)
        break # 最初の1つで終了
