public class ConstructorTest {
	ConstructorTest()
	{
		System.out.println("call Constructor");
	}

	ConstructorTest constructorTest()
	{
		return this;
	}

}
