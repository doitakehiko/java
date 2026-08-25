public class Dog extends Animal {
    public Dog(String name) {
        super(name); // 親クラスのコンストラクタを呼び出す
    }

    // 抽象メソッドを具体的に実装（オーバーライド）
    @Override
    public void makeSound() {
        System.out.println(getName() + "：「ワンワン！」");
    }
}