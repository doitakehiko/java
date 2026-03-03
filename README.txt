cno.javaはBreadMasterの一部であるCountryMasterで参照するテーブルcountry_code_masterのデータを作るプログラムだ

oracleに以下2テーブルがなければならない
country_master
country_code_master


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
country_master_data.txtでcountry_masterデータを作成する必要がある
https://github.com/doitakehiko/BreadMaster/blob/master/BreadMaster/SqlQueries/country_master_data.txt


cno.javaを実行するとcountry_code_masterにインサートされる


使い方
javac ./cno.java
java  cno con.csv

https://github.com/doitakehiko/BreadMaster/blob/master/BreadMaster/SqlQueries/cno.csv

cno.csvデータはソートされていなければならず、ユニークでなければユニーク制約でエラーになる

