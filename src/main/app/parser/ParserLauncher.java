package app.parser;

public class ParserLauncher {

	public static void main(String[] args) {

		/*
		 * add token information. Add regular expressions that match functions, brackets, mathematical operators, integer numbers and variables. Note how each
		 * type of token gets a unique code. Note also how we have to escape special characters in the regular expressions with a double backslash.
		 */
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.add("sin|cos|exp|ln|sqrt", 1); // function
		tokenizer.add("\\(", 2); // open bracket
		tokenizer.add("\\)", 3); // close bracket
		tokenizer.add("[+-]", 4); // plus or minus
		tokenizer.add("[*/]", 5); // mult or divide
		tokenizer.add("\\^", 6); // raised
		tokenizer.add("[0-9]+", 7); // integer number
		tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // variable

		try {
			tokenizer.tokenize(" sin(x) * (1 + var_12) ");

			for (Tokenizer.Token tok : tokenizer.getTokens()) {
				System.out.println("" + tok.token + " " + tok.sequence);
			}
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		}
	}
}
