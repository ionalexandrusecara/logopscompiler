package logoCompiler.lexer;

public abstract class OperatorToken extends Token {
	public abstract String getPostscriptCode();
	public abstract int precedence();
}