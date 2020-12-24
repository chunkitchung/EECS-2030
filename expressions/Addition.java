package expressions;

public class Addition extends Expression {
	
	Addition(int left, int right) {
		super(left, right);
 	}
	
	@Override
	void evaluate() {
		value = left + right;
	}

	Object getValue() {
		return value;
	}
	
}
