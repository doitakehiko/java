fn main() {
    ownershop_sampel();
    ownershop_scope_sampel();
    ownership_move_sample();
}

// simple ownership sample
fn ownershop_sampel() {
    {                      // sは、ここでは有効ではない。まだ宣言されていない
        let s = "hello";   // sは、ここから有効になる 
         // sで作業をする 
         println!("{}", s);//変数 s で所有権のムーブが発生していないため有効
    }                      // このスコープは終わり。もうsは有効ではない
    //println!("{}", s);//スコープ外なのでコンパイルエラー
}

// move ownership sample
fn ownershop_scope_sampel() {
    let s1 = gives_ownership();         // gives_ownershipは、戻り値をs1にムーブする
    println!("{}", s1);                 // 所有しているので実行可能
      
    let s2 = String::from("hello");     // s2がスコープに入る
    println!("{}", s2);                 // 所有しているので実行可能
    println!("{}", s1);                 // s1はまだムーブしていないので実行可能

    let s3 = takes_and_gives_back(s2);  // s2はtakes_and_gives_backにムーブされ、戻り値もs3にムーブされる
    println!("{}", s1);                 // s1は有効
    
    // 【修正】s2は上の行でムーブされたため、以下のコメントアウトを外すとコンパイルエラーになります
    // println!("{}", s2);                 
    
    println!("{}", s3);                 // s3は戻り値を受け取っているので有効
} 
// ここで、s3はスコープを抜け、ドロップされる。s2はムーブされているので、何も起きない。
// s1もスコープを抜け、ドロップされる。

fn gives_ownership() -> String {             // gives_ownershipは、戻り値を呼び出した関数にムーブする
     let some_string = String::from("yours"); // some_stringがスコープに入る
     some_string                              // some_stringが返され、呼び出し元関数にムーブされる
}

// この関数は、Stringを一つ受け取り、返す。
fn takes_and_gives_back(a_string: String) -> String { // a_stringがスコープに入る。
     a_string  // a_stringが返され、呼び出し元関数にムーブされる
}

// ownership sample
fn ownership_move_sample() {
    let s = String::from("hello");  // sがスコープに入る

    takes_ownership(s);             // sの値が関数にムーブされ...
                                    // ... ここではもう有効ではない
    //println!("{}", s);            // 変数 s で所有権のムーブが発生しているため、コンパイルエラー
    
    let x = 5;                      // xがスコープに入る

    makes_copy(x);                  // xも関数にムーブされるが、i32はCopyなので、この後にxを使っても大丈夫
    println!("{}", x)
} 
// ここでxがスコープを抜け、sもスコープを抜ける。ただし、sの値はムーブされているので、何も特別なことは起こらない。

fn takes_ownership(some_string: String) { // some_stringがスコープに入る。
    println!("{}", some_string);
} // ここでsome_stringがスコープを抜け、`drop`が呼ばれる。後ろ盾してたメモリが解放される。

fn makes_copy(some_integer: i32) { // some_integerがスコープに入る
    println!("{}", some_integer);
} // ここでsome_integerがスコープを抜ける。何も特別なことはない。

/*fn main() {
    ownershop_sampel();
    ownershop_scope_sampel();
    ownership_move_sample();
}
// simple ownership sample
fn ownershop_sampel() {
    {                      // sは、ここでは有効ではない。まだ宣言されていない
        let s = "hello";   // sは、ここから有効になる

        // sで作業をする

        println!("{}", s);//変数 s で所有権のムーブが発生していないため有効
    }                      // このスコープは終わり。もうsは有効ではない
    //println!("{}", s);//スコープ外なのでコンパイルエラー
}
// move ownership sample
fn ownershop_scope_sampel() {
    let s1 = gives_ownership();         // gives_ownershipは、戻り値をs1に
                                        // ムーブする
    println!("{}", s1);//所有しているので実行可能


    let s2 = String::from("hello");     // s2がスコープに入る
    println!("{}", s2);//所有しているので実行可能
    println!("{}", s1);//所有権がムーブされていないので実行可能

    let s3 = takes_and_gives_back(s2);  // s2はtakes_and_gives_backにムーブされ
                                        // 戻り値もs3にムーブされる
    println!("{}", s1);//
    //println!("{}", s2);//
    println!("{}", s3);//

} // ここで、s3はスコープを抜け、ドロップされる。s2はムーブされているので、何も起きない。
  // s1もスコープを抜け、ドロップされる。

fn gives_ownership() -> String {             // gives_ownershipは、戻り値を
                                             // 呼び出した関数にムーブする

    let some_string = String::from("yours"); // some_stringがスコープに入る

    some_string                              // some_stringが返され、呼び出し元関数に
                                             // ムーブされる
}



// この関数は、Stringを一つ受け取り、返す。
fn takes_and_gives_back(a_string: String) -> String { // a_stringがスコープに入る。

    a_string  // a_stringが返され、呼び出し元関数にムーブされる
}

// ownership sample
fn ownership_move_sample() {
    let s = String::from("hello");  // sがスコープに入る

    takes_ownership(s);             // sの値が関数にムーブされ...
                                    // ... ここではもう有効ではない

    //println!("{}", s);//変数 s で所有権のムーブが発生しているため、コンパイルエラー
    let x = 5;                      // xがスコープに入る

    makes_copy(x);                  // xも関数にムーブされるが、
                                    // i32はCopyなので、この後にxを使っても
                                    // 大丈夫
    println!("{}", x)
} // ここでxがスコープを抜け、sもスコープを抜ける。ただし、sの値はムーブされているので、
  // 何も特別なことは起こらない。

fn takes_ownership(some_string: String) { // some_stringがスコープに入る。
    println!("{}", some_string);
} // ここでsome_stringがスコープを抜け、`drop`が呼ばれる。後ろ盾してたメモリが解放される。
  // 後ろ盾してたメモリが解放される。

fn makes_copy(some_integer: i32) { // some_integerがスコープに入る
    println!("{}", some_integer);
} // ここでsome_integerがスコープを抜ける。何も特別なことはない。
*/
