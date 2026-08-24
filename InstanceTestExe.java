public class InstanceTestExe {
	public static void main(String[] args) {
		InstanceTest instanceTest = new InstanceTest(1, "Alice");
		InstanceTest instanceTestPre = instanceTest;
		System.out.println( "getId = " + instanceTest.getId());
		System.out.println( "getName = " + instanceTest.getName());
		instanceTest.setId(2);
		instanceTest.setName("Bob");
		System.out.println( "getId = " + instanceTest.getId());
		System.out.println( "getName = " + instanceTest.getName());

		System.out.println(instanceTest == instanceTestPre); // 完全に同じ物体なら true
	        System.out.println(instanceTest.getClass() == instanceTestPre.getClass());// 完全に同じ物体なら true

		if(instanceTest.getId() == instanceTestPre.getId())
		{
			System.out.println(":instanceTestのIdとinstanceTestPreのidは一致している");
		}
		if(instanceTest.getName().equals(instanceTestPre.getName()))//文字列比較はequalsを使えとAIにより補足
		{
			System.out.println(":instanceTestのNameとinstanceTestPreのNameは一致している");
		}
		InstanceTest instanceTestNew = new InstanceTest(3, "John");
		System.out.println(instanceTest == instanceTestNew); // 別のメモリを参照しているのでfalse
	        System.out.println(instanceTest.getClass() == instanceTestNew.getClass());// クラスは同じなのでtrue

		if(instanceTest.getId() != instanceTestNew.getId())
		{
			System.out.println(":instanceTestのIdとinstanceTestNewのidは一致していない");
		}
		if(!instanceTest.getName().equals(instanceTestNew.getName()))//文字列比較はequalsを使えとAIにより補足
		{
			System.out.println(":instanceTestのNameとinstanceTestNewのNameは一致していない");
		}

		//InstanceTest instanceTest = new InstanceTest(2, "Bob");
		instanceTest.setId(2);
		instanceTest.setName("Bob");

		System.out.println("\n--- 🧪 実験1: メソッド内で『セッター』を使ったらどうなる？ ---");
		System.out.println("【呼び出し前】Id = " + instanceTest.getId()); // 2
		
		changeContent(instanceTest); // メソッド呼び出し
		
		System.out.println("【呼び出し後】Id = " + instanceTest.getId()); // 🤔 2のまま？ それとも99に変わる？


		System.out.println("\n--- 🧪 実験2: メソッド内で『別のオブジェクトを代入』したらどうなる？ ---");
		System.out.println("【呼び出し前】Name = " + instanceTest.getName()); // Bob
		
		changeReference(instanceTest); // メソッド呼び出し
		
		System.out.println("【呼び出し後】Name = " + instanceTest.getName()); // 🤔 Daveに変わる？ それともBobのまま？

		instanceTest = changeReferenceCorrect(instanceTest); 
		
		System.out.println("【呼び出し後】Name = " + instanceTest.getName()); //

	}
	// 【メソッド①】中身（実体）を書き換えるメソッド
	public static void changeContent(InstanceTest target) {
		// targetという変数（ポインタ）の指す先にある実体を書き換える
		target.setId(99); 
		System.out.println("[メソッド内] targetのIdを99に書き換えました");
	}

	// 【メソッド②】参照（指し示す住所）そのものを書き換えるメソッド
	public static void changeReference(InstanceTest target) {
		// targetという変数（ポインタ）に、新しくnewした別の住所を上書きする
		target = new InstanceTest(4, "Dave"); 
		System.out.println("[メソッド内] targetに新しいインスタンス(Dave)を代入しました");
		System.out.println("[メソッド内] targetのName = " + target.getName()); // Dave
	}
	// 🔄 修正版：InstanceTest型を返すメソッドにする
	public static InstanceTest changeReferenceCorrect(InstanceTest target) {
		// 新しいインスタンス（Dave）を作って target に入れる
		target = new InstanceTest(4, "Dave"); 
		System.out.println("[メソッド内] targetに新しいインスタンス(Dave)を代入しました");
		
		// 📢 新しいオブジェクトの住所を呼び出し元に返却する
		return target; 
	}

}
/*
InstanceTest instanceTestPre = instanceTest;
同じメモリを参照するので値を変えても
instanceTest == instanceTestPre
はtrueを返す
.getClass() は、そのインスタンスがどのクラスから作られたか（設計図の正体）を返す
同じ設計図から作られた: どちらの変数も InstanceTest という同じクラスから作られたインスタンスを指している（しかも今回は全く同じ同一インスタンスである）ため、クラスが一致し true になります。

セッターは
https://github.com/doitakehiko/java/blob/master/pointertest.c
の行番号29
*p = 50;
が行われている

操作Java (今回のコード)　　　　　　　　　　　　　C言語(pointertest.c)アドレスのコピー
InstanceTest instanceTestPre = instanceTest;　　　int* p = &a; 
（同じ場所を指す）実態の書き換え
instanceTest.setId(2);　　　　　　　　　　　　　　*p = 50; （指す先の中身を変える）


instanceTestPreはinstanceTest両方ともオブジェクト型変数(参照型変数)だ
instanceTestPre = instanceTestで両オブジェクト変数とも同じオブジェクトのメモリを参照する
両オブジェクトのゲッターセッターは同じオブジェクト(つまりメモリの同じ位置)を参照し書き換える
オブジェクトを2個作りたい場合は新しオブジェクトをnewしないとダメだ
*/

