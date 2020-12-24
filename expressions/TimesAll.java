package expressions;

public class TimesAll extends ExpressionCollector {
	@Override
	void evaluate() {
		int product = 1;
		for (Expression expression : this.expressions) {
			expression.evaluate();
			product *= (int) expression.getValue();
		}
		this.value = product;
	}
}
