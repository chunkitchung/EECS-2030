package expressions;

public class DisjoinAll extends ExpressionCollector {
	@Override
	void evaluate() {
		boolean result = false;
		for (Expression expression : this.expressions) {
			expression.evaluate();
			result |= (boolean)expression.value;
		}
		this.value = result;
	}
}
