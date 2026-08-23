public class ConstructorTestExe {
	public static void main(String[] args) {
		ConstructorTest myConstructor = new ConstructorTest();
		ConstructorTest camelCaseConstructor = myConstructor.constructorTest();

		System.out.println(myConstructor == camelCaseConstructor); // 完全に同じ物体なら true
	        System.out.println(myConstructor.getClass() == camelCaseConstructor.getClass());// 完全に同じ物体なら true

		System.out.println(myConstructor instanceof ConstructorTest);  //ConstructorTest型なので出力: true 
		System.out.println(camelCaseConstructor instanceof ConstructorTest);  //ConstructorTest型なので出力: true 

		Object obj1 = "Java 15以前テスト";

		if (obj1 instanceof String) {
		    String str = (String) obj1; // 安全にキャストできる
		    System.out.println(str.length());
		}



		Object obj2 = "Java 16以降：パターンマッチング";

		// 一行で「判定」と「変数への代入」を同時に行う
		if (obj2 instanceof String str) {
		    System.out.println(str.length()); // そのまま str として使える！
		}

		Object obj3 = 1;
		if (!(obj3 instanceof String)) {
		    System.out.println("obj3 is int type.");
		}

		String emptyText = null;
		System.out.println(emptyText instanceof String); // nullの場合かならず出力: false
	}
}
/*
以下AIより引用

現在のモダンなJava（JVM）においては、キャストの処理コストを気にする必要はほぼ完全になくなっています。

2. 「現在」はなぜ気にしなくて良いのか？
・JITコンパイラによる最適化
プログラムが実行されると、JVMは「あ、この変数にはいつも ConstructorTest クラスしか入ってこないな」ということを学習します。すると、2回目以降は型チェックの処理そのものをバッサリ省略（省略コードに書き換え）します。
・コストはほぼゼロに
この最適化（型推論のインライン化など）のおかげで、現代のキャストのコストは数ナノ秒以下、あるいは事実上の「ゼロ」に等しいレベルまで高速化されています。


3.  今でもキャストが嫌われる「本当の理由」現代の開発でも「キャストは使うな」と言われることがありますが、それは性能（スピード）の理由ではなく、コードの安全性（設計）の理由に変わっています。
・実行時エラー（ClassCastException）の危険性：コンパイルの段階ではエラーにならず、プログラムを動かした瞬間に「型が違います」と強制終了してしまうバグの原因になります。
・ジェネリクスで解決できる：Java 5以降、List<String> のように型を指定できるようになり、そもそも手動でキャストを書く必要性自体が激減しました。
もしコードを綺麗にしたい場合は、前回紹介した Java 16以降の「パターンマッチング（if (obj instanceof String str)）」 を使うのが現代のベストプラクティス
*/

