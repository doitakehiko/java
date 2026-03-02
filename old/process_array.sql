CREATE OR REPLACE PROCEDURE process_array (
    p_data IN number_array
) AS
BEGIN
    -- 配列の要素を処理するコード
    FOR i IN p_data.FIRST .. p_data.LAST LOOP
        DBMS_OUTPUT.PUT_LINE('Element ' || i || ': ' || p_data(i));
    END LOOP;
END;
