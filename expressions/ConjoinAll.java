package expressions;

public class ConjoinAll extends ExpressionCollector {
	@Override
	void evaluate() {
		boolean result = true;
		for (Expression expression : this.expressions) {
			expression.evaluate();
			result &= (boolean)expression.value;
		}
		this.value = result;
	}
}
