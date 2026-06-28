#!/bin/bash

URL_FILE="./url_list.txt"
TARGET_HOST="http://192.168.1.64"

if [ ! -f "$URL_FILE" ]; then
    echo "エラー: $URL_FILE が見つかりませんわ！"
    exit 1
fi

TOTAL_COUNT=$(tr -d '\r' < "$URL_FILE" | grep -v '^[[:space:]]*$' | wc -l)

echo "🚀 10Gネットワーク経由での20並列「超ストリームまとめ投げ」を開始しますわ！（合計 ${TOTAL_COUNT} 件）"
START_TIME=$(date +%s)

# 💡 修正ポイント: 一時ファイルを1つも作りません！
# 1. データを綺麗にしてURLの形にする
# 2. xargs -n 200 で200行ずつの「引数」として curl に直接渡す
# 3. curl は複数のURLを引数として渡されると、自動的に1つのプロセスでキープアライブ（使い回し）して連続アクセスします
# ※ -d '\n' を外すことで、xargs がスペース（改行）区切りで複数の引数をまとめて curl に渡せるようになります

tr -d '\r' < "$URL_FILE" | grep -v '^[[:space:]]*$' | sed "s|^|${TARGET_HOST}|; s/ /%20/g" | \
xargs -P 20 -n 200 curl -s --connect-timeout 2 --max-time 5 -o /dev/null

END_TIME=$(date +%s)
ELAPSED=$((END_TIME - START_TIME))

echo "✅ 完全終了！合計 ${TOTAL_COUNT} 件のセッションを ${ELAPSED} 秒で10G再生完了しましたわ！"
