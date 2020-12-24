package expressions;

public class AddAll extends ExpressionCollector {
	@Override
	void evaluate() {
		int sum = 0;
		for (Expression expression : this.expressions) {
			expression.evaluate();
			sum += (int) expression.getValue();
		}
		this.value = sum;
	}
}
