CREATE OR REPLACE PROCEDURE kill_app_zombie_sessions AS
    -- 【設定】最後の通信から何秒以上経過したINACTIVEセッションを対象にするか（例：2時間 = 7200秒）
    v_threshold_seconds NUMBER := 7200; 
    
    -- 【設定】TRUE: 実際にKILLする / FALSE: 画面表示のみ（テスト用）
    v_execute_kill      BOOLEAN := FALSE; 
    
    v_sql               VARCHAR2(200);
BEGIN
    FOR r IN (
        SELECT 
            sid, 
            serial#, 
            username, 
            machine, 
            program, 
            last_call_et AS inactive_seconds
        FROM v$session
        WHERE status = 'INACTIVE'                
          AND type = 'USER'                      
          AND username IS NOT NULL               
          AND last_call_et > v_threshold_seconds 
          -- 【推奨】特定のアプリやサーバーに絞る場合はコメントを解除して指定
          -- AND program LIKE 'MyApp%' 
          -- AND machine = 'AppServer01'
    ) LOOP
        
        -- KILLコマンドの組み立て
        v_sql := 'ALTER SYSTEM KILL SESSION ''' || r.sid || ',' || r.serial# || ''' IMMEDIATE';
        
        IF v_execute_kill THEN
            BEGIN
                EXECUTE IMMEDIATE v_sql;
            EXCEPTION
                WHEN OTHERS THEN
                    -- エラー発生時はアラートログや独自ログテーブルに出力することを推奨
                    NULL;
            END;
        END IF;
        
    END LOOP;
END;
/
