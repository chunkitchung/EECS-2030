package eecs2030.lab3;

/**
 * A class that represents two dimensional spatial vectors (a direction and a
 * magnitude). Every vector has a real-valued x-component and a y-component. The
 * class provides some basic mathematical operations such as vector addition and
 * subtraction, and scalar multiplication.
 * 
 * @author EECS2030 Fall 2020
 * 
 */
public final class Vector2 {

	// NOTE: Before you get started with the constructors, implement the class
	// variables and
	// ===== the accessor methods (getX,getY). The tester for the constructors
	// relies on these
	// methods being implemented. After this, move ahead with the constructors

	// class variables here
	private double x;
	private double y;

	/**
	 * Creates the vector <code>(0.0, 0.0)</code>.
	 */
	public Vector2() {
		this(0.0, 0.0);
	}

	/**
	 * Creates the vector <code>(x, y)</code>.
	 * 
	 * @param x the x-component of the vector
	 * @param y the y-component of the vector
	 */
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a vector with the same components as another vector.
	 * 
	 * @param other a vector to copy the components from
	 */
	public Vector2(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}

	/**
	 * Returns the x component of the vector.
	 * 
	 * @return the x component of the vector.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Sets the x component of the vector.
	 * 
	 * @param x the new value of the x component.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y component of the vector.
	 * 
	 * @return the y component of the vector.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Sets the y component of the vector.
	 * 
	 * @param y the new value of the y component.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Sets the x and y component of the vector.
	 * 
	 * @param x the new value of the x component.
	 * @param y the new value of the y component.
	 */
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Add a vector to this vector changing the components of this vector.
	 * 
	 * <p>
	 * Mathematically, if this vector is <code>a</code> and the other vector is
	 * <code>b</code> then invoking this method is equivalent to computing
	 * <code>a + b</code> and assigning the value back to <code>a</code>.
	 * 
	 * @param other the vector to add to this vector.
	 * @return this <code>Vector2D</code> object
	 */
	public Vector2 add(Vector2 other) {
		// set(this.getX() + other.getX(), this.getY() + other.getY());
		// return this;
		this.x += other.x;
		this.y += other.y;
		return this;
	}

	/**
	 * Subtract a vector from this vector changing the components of this vector.
	 * 
	 * <p>
	 * Mathematically, if this vector is <code>a</code> and the other vector is
	 * <code>b</code> then invoking this method is equivalent to computing
	 * <code>a - b</code> and assigning the value back to <code>a</code>.
	 * 
	 * @param other the vector to subtract this vector.
	 * @return this <code>Vector2D</code> object
	 */
	public Vector2 subtract(Vector2 other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	/**
	 * Multiply this vector by a scalar amount changing the components of this
	 * vector.
	 * 
	 * <p>
	 * Mathematically, if this vector is <code>a</code> and the scalor is
	 * <code>s</code> then invoking this method is equivalent to computing
	 * <code>s * a</code> and assigning the value back to <code>a</code>.
	 * 
	 * @param s the scalar value to multiply the vector by
	 * @return this <code>Vector2D</code> object
	 */
	public Vector2 multiply(double s) {
		this.x *= s;
		this.y *= s;
		return this;
	}

	/**
	 * Returns the magnitude of this vector.
	 * 
	 * @return the magnitude of this vector.
	 */
	public double mag() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}

	/**
	 * Returns a new <code>Vector2D</code> equal to <code>a + b</code>.
	 * 
	 * @param a a vector
	 * @param b another vector
	 * @return a new <code>Vector2D</code> equal to <code>a + b</code>
	 */
	public static Vector2 add(Vector2 a, Vector2 b) {
		return new Vector2(a.getX() + b.getX(), a.getY() + b.getY());
	}

	/**
	 * Returns a new <code>Vector2D</code> equal to <code>a - b</code>.
	 * 
	 * @param a a vector
	 * @param b another vector
	 * @return a new <code>Vector2D</code> equal to <code>a - b</code>
	 */
	public static Vector2 subtract(Vector2 a, Vector2 b) {
		return new Vector2(a.getX() - b.getX(), a.getY() - b.getY());
	}

	/**
	 * Returns a new <code>Vector2D</code> equal to <code>s * a</code>.
	 * 
	 * @param s a scalar
	 * @param a a vector
	 * @return a new <code>Vector2D</code> equal to <code>s * a</code>
	 */
	public static Vector2 multiply(double s, Vector2 a) {
		return new Vector2(a.getX() * s, a.getY() * s);
	}

	/**
	 * Returns the vector having magnitude one pointing in the direction
	 * <code>theta</code> degrees from the x axis.
	 * 
	 * <p>
	 * The components of the vector are equal to
	 * <code>(Math.cos(rad), Math.sin(rad))</code> where <code>rad</code> is
	 * <code>theta</code> expressed in radians.
	 * 
	 * @param theta the direction that the vector is pointing in measured in degrees
	 *              from the x axis
	 * @return the unit vector pointing in the given direction
	 */
	public static Vector2 dirVector(double theta) {
		double rad = theta*(Math.PI/180);
		return new Vector2(Math.cos(rad), Math.sin(rad));
	}

	/**
	 * Returns a string representation of the vector. The string is the name of the
	 * vector, followed by the comma separated components of the vector inside
	 * parentheses.
	 * 
	 * @return a string representation of the vector
	 */
	@Override
	public String toString() {
		String strVector = "(" + this.getX() + ", " + this.getY() + ")";
		return strVector;
	}

	/**
	 * Determines if two vectors are almost equal (similar). Two vectors are similar
	 * if the magnitude of their vector difference is smaller than the specified
	 * tolerance.
	 * 
	 * @param other the other vector to compare
	 * @param tol   the threshold length of the vector difference
	 *              <code>(this - other)</code>
	 * @return <code>true</code> if the length of <code>(this - other)</code> is
	 *         less than <code>tol</code>, and <code>false</code> otherwise
	 */
	public boolean similarTo(Vector2 other, double tol) {
		double diff = Math.abs(this.mag() - other.mag());
		if(diff < tol) {
			return true;
		}
		return false;
	}

}
