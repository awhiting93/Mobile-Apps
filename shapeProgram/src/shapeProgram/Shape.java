package shapeProgram;

public class Shape 
{
	private String mName;
	private int    mTotal,
	               mUnique;
	private float  mArea,
		 		   mPerimeter;
	
	public void show_shape() 
	{
		System.out.println("\n\nThe specifications of a " + mName + " shape are:");
		System.out.println("----------------------------------------------");
		System.out.println("Name of the shape:      \"" + mName + "\"");
		System.out.println("Total number of sides:  " + getTotal());
		System.out.println("Number of unique sides: " + getUnique());
		System.out.println("Area:                   " + getArea());
		System.out.println("Perimeter:              " + getPerimeter());
	};
	
	// Getters and setters
	public String getName() {return mName;}
	public void setName(String name) {this.mName = name;}
	public int getTotal(){return mTotal;}
	public void setTotal(int total) {this.mTotal = total;}
	public int getUnique() {return mUnique;}
	public void setUnique(int unique) {this.mUnique = unique;}
	public float getArea() {return mArea;}
	public void setArea(float area) {this.mArea = area;}
	public float getPerimeter() {return mPerimeter;}
	public void setPerimeter(float perimeter) {this.mPerimeter = perimeter;}

	public static void main(String[] args) 
	{
		RightTriangle rightTriangle = new RightTriangle("TRIANGLE-1", 5.99f, 11.99f);
		Square square = new Square("SQUARE-1", 11.99f);
		Rectangle rectangle = new Rectangle("RECTANGLE-1", 11.99f, 5.99f);
		
		rightTriangle.show_shape();
		square.show_shape();
		rectangle.show_shape();
		return;
	}

}
