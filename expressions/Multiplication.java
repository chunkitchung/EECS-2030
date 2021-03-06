package expressions;

public class Multiplication extends Expression {

	Multiplication(int left, int right) {
		super(left, right);
 	}

	@Override
	void evaluate() {
		value = left * right;
	}

	Object getValue() {
		return value;
	}
}
