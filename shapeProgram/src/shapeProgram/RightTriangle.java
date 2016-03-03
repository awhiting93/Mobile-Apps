/**
 * 
 */
package shapeProgram;

/**
 * @author awhiting18
 *
 */
public class RightTriangle extends Shape 
{
	private float mBase,
				  mHeight,
				  mHypotenuse;
	
	public RightTriangle(String shape, float rt_base, float rt_height)
	{
		System.out.printf("\nCreating right triangle shape \"%s\"", shape);
		System.out.printf("\n   with base = %.2f and height = %.2f", rt_base, rt_height);
		
		mBase = rt_base;
		mHeight = rt_height;
		this.setTotal(3);
		this.setUnique(3);
		this.setName("right triangle");
		mHypotenuse = (float)Math.sqrt((double)(mBase * mBase) + (mHeight * mHeight));
		this.setArea(0.5f * (mBase * mHeight));
		this.setPerimeter(mBase + mHeight + mHypotenuse);
	};
	
	public void show_shape()
	{
		super.show_shape();
		System.out.println("Length of base:         " + mBase);
		System.out.println("Length of height:       " + mHeight);
		System.out.println("Length of hypotenuse:   " + mHypotenuse);
		return;
	};
	
}
