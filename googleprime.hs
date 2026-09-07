import Data.Char (digitToInt)

-- 1. e の値の（小数点以下を含む）巨大な文字列（※一部抜粋）
-- 実際は数千桁必要ですが、ここではデモ用に一部を記載
eString :: String
eString = "2718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059"

-- 2. 単一の数値（Integer）が素数かどうかを高速に判定する関数（試し割り法）
-- √n までの奇数で割るだけなので、10桁の数でも一瞬で終わります
isPrime :: Integer -> Bool
isPrime n
    | n <= 1    = False
    | n == 2    = True
    | even n    = False
    | otherwise = not (any (\d -> n `mod` d == 0) [3, 5 .. floor (sqrt (fromIntegral n))])

-- 3. 文字列から連続する10桁を切り出して Integer のリストにする関数
subStrings10 :: String -> [Integer]
subStrings10 xs
    | length xs < 10 = []
    | otherwise      = toInteger10 (take 10 xs) : subStrings10 (tail xs)
  where
    -- 文字列を数値に変換する補助関数
    toInteger10 :: String -> Integer
    toInteger10 s = foldl (\acc d -> acc * 10 + fromIntegral (digitToInt d)) 0 s

-- 4. メイン処理
main :: IO ()
main = do
    -- 10桁の数値リストを作成
    let candidates = subStrings10 eString
    -- 最初に「isPrime」が True になるものを探す
    case filter isPrime candidates of
        (answer:_) -> putStrLn $ "発見した10桁の素数: " ++ show answer
        []         -> putStrLn "10桁の素数は見つかりませんでした。"

