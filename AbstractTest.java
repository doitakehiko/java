public class AbstractTest {
	public static void main(String[] args) {
		Cat cat = new Cat("Luna");
		cat.sleep();
		cat.makeSound();

		Dog dog = new Dog("Max");
		dog.sleep();
		dog.makeSound();
	}
}
