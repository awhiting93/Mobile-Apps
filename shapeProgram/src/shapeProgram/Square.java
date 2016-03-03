/**
 * 
 */
package shapeProgram;

/**
 * @author awhiting18
 *
 */
public class Square extends Shape 
{
	private float mSide;
	
	public Square(String shape, float s_side)
	{
		System.out.printf("\nCreating square shape \"%s\"", shape);
		System.out.printf("\n   with side = %.2f", s_side);
		
		mSide = s_side;
		this.setName("square");
		this.setTotal(4);
		this.setUnique(1);
		this.setArea(mSide * mSide);
		this.setPerimeter(mSide * 4.0f);
	};
	
	public void show_shape()
	{
		super.show_shape();
		System.out.println("Length of a side:       " + mSide);
		return;
	};
}
