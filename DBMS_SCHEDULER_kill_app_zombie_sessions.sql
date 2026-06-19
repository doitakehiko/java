BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'JOB_KILL_ZOMBIE_SESSIONS',        -- ジョブ名（任意の名前）
        job_type        => 'STORED_PROCEDURE',                -- プログラムの種類
        job_action      => 'KILL_APP_ZOMBIE_SESSIONS',         -- 実行するプロシージャ名
        start_date      => SYSTIMESTAMP,                       -- 開始日時（今すぐ）
        repeat_interval => 'FREQ=HOURLY; INTERVAL=1',          -- 実行間隔（1時間ごと）
        end_date        => NULL,                               -- 終了日時（なし）
        enabled         => TRUE,                               -- 作成と同時に有効化
        comments        => 'アプリのゾンビセッションを自動KILLするジョブ'
    );
END;
/