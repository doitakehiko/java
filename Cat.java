public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    // 抽象メソッドを具体的に実装（オーバーライド）
    @Override
    public void makeSound() {
        System.out.println(getName() + "：「ニャーオ」");
    }
}