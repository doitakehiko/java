public class InstanceTest {
	private int _id;
	private String _name;
	InstanceTest(int id , String name)
	{
		System.out.print("constractor");
		_id = id;
		System.out.print(":set _id = " + _id );
		_name = name;
		System.out.println(":set _name = " + _name );
	}
	public int getId()
	{
		System.out.println("return _id is " + _id );
		return _id;
	}
	public String getName()
	{
		System.out.println("return_name is " + _name );
		return _name;
	}
	public void setId(int id)
	{
		_id = id;
		System.out.println("set _id = " + _id );
	}
	public void setName(String name)
	{
		_name = name;
		System.out.println("set _name = " + _name );
	}
}
