package expressions;

public class GreaterThan extends Expression {

	GreaterThan(int left, int right) {
		super(left, right);
	}

	@Override
	void evaluate() {
		value = (left>right);
	}

	Object getValue() {
		return value;
	}
}
