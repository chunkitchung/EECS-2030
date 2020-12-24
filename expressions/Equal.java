package expressions;

public class Equal extends Expression {

	Equal(int left, int right) {
		super(left, right);
	}

	@Override
	void evaluate() {
		value = (left == right);
	}

	Object getValue() {
		return value;
	}
}
