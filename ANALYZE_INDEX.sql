ANALYZE INDEX スキーマ名.インデックス名 VALIDATE STRUCTURE;

SELECT 
    name AS "インデックス名",
    blocks AS "総ブロック数",
    lf_rows AS "有効なリーフ行数",
    del_lf_rows AS "削除されたリーフ行数",
    -- 削除された行の割合（断片化率）
    ROUND((del_lf_rows / decode(lf_rows, 0, 1, lf_rows)) * 100, 2) AS "断片化率(%)",
    -- 領域の利用効率
    pct_used AS "領域使用率(%)"
FROM index_stats;

SELECT 
    df.tablespace_name AS "表領域名",
    df.file_name AS "データファイル名",
    fs.phyrds AS "物理読込回数",
    fs.phywrts AS "物理書込回数",
    -- 総I/O数
    (fs.phyrds + fs.phywrts) AS "総I/O回数",
    fs.readtim AS "読込合計時間(厘秒)",
    fs.writetim AS "書込合計時間(厘秒)"
FROM v$filestat fs
JOIN dba_data_files df ON fs.file# = df.file_id
ORDER BY "総I/O回数" DESC;


-- 1. 特定のインデックスの監視を開始
ALTER INDEX スキーマ名.インデックス名 MONITORING USAGE;

-- 2. しばらく業務を運用した後、使用されたか確認
SELECT 
    index_name AS "インデックス名",
    table_name AS "対象テーブル",
    monitoring AS "監視状態",
    used AS "使用有無(YES/NO)",
    start_monitoring AS "監視開始時間"
FROM v$object_usage;

-- 3. 調査が終わったら監視を停止
ALTER INDEX スキーマ名.インデックス名 NOMONITORING USAGE;

