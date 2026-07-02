cno.javaはBreadMasterの一部であるCountryMasterで参照するテーブルcountry_code_masterのデータを作るプログラムだ

oracleに以下2テーブルがなければならない
country_master
country_code_master


テーブル作成sqlは以下ディレクトリにある
https://github.com/doitakehiko/BreadMaster/blob/master/BreadMaster/SqlQueries/


cno.javaと同ディレクトリに以下ファイルを作成する必要がある
DoConnection.java
interface DoConnection {
	final static String url = "jdbc:oracle:thin:@youroraclehost:1521/yourCONTAINER";
	final static String user = "yourusername";
	final static String password = "yourpassword";
	static String getUrl() {
		return url;
	}
	static String getUser() {
		return user;
	}
	static String getPassword() {
		return password;
	}
}
country_master_data.sqlでcountry_masterデータを作成する必要がある
https://github.com/doitakehiko/BreadMaster/blob/master/BreadMaster/SqlQueries/country_master_data.sql

cno.javaをcno.sqlに移植した
https://github.com/doitakehiko/BreadMaster/blob/master/BreadMaster/SqlQueries/cno.sql


cno.javaを実行するとcountry_code_masterにインサートされる
使い方
javac ./cno.java
java  cno con.csv

https://github.com/doitakehiko/BreadMaster/blob/master/BreadMaster/SqlQueries/cno.csv

cno.csvデータはソートされていなければならず、ユニークでなければユニーク制約でエラーになる



https://share.google/aimode/0ETXz9rJUEb9sQ8Uk
sort_cache.c

SafeApacheStressTest.sh
を外部サーバーに向けて実行してはいけない
クラッキング行為とプロバイダーからみなされてインターネット接続を停止されるかもしれない
グローバルipアドレスに全ポートスキャンするのもクラッキング行為として同じ対応をされる恐れがある
あくまでホームラボのテストサーバーに向けて実行するスクリプトだ
イントラネット内でも稼働中のサーバーに実行してはならない
最悪サーバーが停止する恐れがあると肝に銘じろ


ProxySQL.txt
ProxySQLでsqlロードバランサ―構築およびC#サンプルコード

