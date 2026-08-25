public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    // 共通のメソッド（すべての動物で共通の処理）
    public void sleep() {
        System.out.println(name + "は眠っています。 zzz...");
    }

    // 抽象メソッド（子クラスに実装を強制する中身のないメソッド）
    public abstract void makeSound();

    // ゲッター
    public String getName() {
        return name;
    }
}
