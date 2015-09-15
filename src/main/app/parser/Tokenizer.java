package app.parser;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * http://cogitolearning.co.uk/?p=523 Prerequis : regular expression
 * 
 * @author I310911
 *
 */
public class Tokenizer {

	/**
	 * Hold the information for all the tokens
	 */
	private LinkedList<TokenInfo> tokenInfos;

	/**
	 * Hold encountered tokens
	 */
	private LinkedList<Token> tokens;
	
	public Tokenizer() {
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
	}

	/**
	 * Add the token information in our list. <br />
	 * Set the “^” character to the user supplied regular expression. It causes the regular expression to match only the beginning of a string. This is needed
	 * because we will be removing any token always looking for the next token at the beginning of the input string.
	 * 
	 * @param regex
	 * @param token
	 */
	public void add(String regex, int token) {
		tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
	}

	private class TokenInfo {
		/**
		 * We store Pattern objects instead of the regular expression string to improve performance. Regular expressions have to be compiled which takes time.
		 * Pattern stores the regular expression in compiled form
		 */
		public final Pattern regex;
		/**
		 * The code of the token is given by the integer value ‘token’. Each type of token should have its own code
		 */
		public final int token;

		/**
		 * The regular expression that is used to match the input string against the token is stored in the Pattern regex
		 * 
		 * @param regex
		 * @param token
		 */
		public TokenInfo(Pattern regex, int token) {
			super();
			this.regex = regex;
			this.token = token;
		}
	}

	/**
	 * Store the information about the tokens we have seen. We need to store the token code and the string that corresponds to the token. The string is needed
	 * because the token code does not retain the full information about the input. When we have found a variable we will give the token a special code for
	 * variable tokens but we need to also keep the name of the variable so that we can later use it to store or retrieve information.
	 * 
	 * @author I310911
	 *
	 */
	public class Token {
		public final int token;
		public final String sequence;

		public Token(int token, String sequence) {
			super();
			this.token = token;
			this.sequence = sequence;
		}
	}
}