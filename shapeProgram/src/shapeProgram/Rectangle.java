/**
 * 
 */
package shapeProgram;

/**
 * @author awhiting18
 *
 */
public class Rectangle extends Shape 
{

	private float mBase,
				  mHeight;
	
	public Rectangle(String shape, float r_base, float r_height)
	{
		System.out.printf("\nCreating square shape \"%s\"", shape);
		System.out.printf("\n   with base = %.2f and height = %.2f", r_base, r_height);
		
		mBase = r_base;
		mHeight = r_height;
		this.setName("rectangle");
		this.setTotal(4);
		this.setUnique(2);
		this.setArea(mBase * mHeight);
		this.setPerimeter((2.0f * mBase) + (2.0f * mHeight));
	};
	
	public void show_shape()
	{
		super.show_shape();
		System.out.println("Length of base:         " + mBase);
		System.out.println("Length of height:       " + mHeight);
		return;
	};
}
