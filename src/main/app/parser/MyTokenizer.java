package app.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://cogitolearning.co.uk/?p=523 Prerequis : regular expression
 * 
 * @author I310911
 *
 */
public class MyTokenizer {

	/**
	 * Hold the information for all the tokens
	 */
	private LinkedList<TokenInfo> tokenInfos;

	/**
	 * Hold encountered tokens<br/>
	 * By storing the token information in a linked list, and always ensuring that we look for matches from the beginning of the list, we are giving precedence
	 * to patterns added first.
	 */
	private LinkedList<Token> tokens;

	public MyTokenizer() {
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

	/**
	 * To access the result of tokenizing an input string
	 * 
	 * @return result of the tokenized String
	 */
	public LinkedList<Token> getTokens() {
		return tokens;
	}

	/**
	 * Tokenize an input String
	 * 
	 * @param str
	 */
	public void tokenize(String str) {
		// we first define a local string that contains the input string but without any leading or trailing spaces. Also we clear the tokens list from any
		// previous data.
		// String s = new String(str);
		String s = str.trim();
		tokens.clear();

		// The main loop is carried out until the local input string is empty. When we find a token we will remove it from the front of the string. If we are
		// successful and the whole string could be tokenized then we will eventually end up with an empty string.
		while (!s.equals("")) {
			// The match variable indicates if any of the tokens provided a match with the beginning of the input string. Initially we have no match.
			boolean match = false;

			// We now loop through all the token infos.
			for (TokenInfo info : tokenInfos) {
				// and create a Matcher for the input string.
				Matcher m = info.regex.matcher(s);
				// If the pattern is found in the input string then match is set to true.
				if (m.find()) {
					match = true;

					// The group() method of the Matcher returns the string of the last match.
					String tok = m.group().trim();

					// A new Token object is appended to the list of tokens. The token object contains the code of the token that resulted in the match and the
					// matched string.
					tokens.add(new Token(info.token, tok));

					// the matcher is used to remove the token from the beginning of the input string. We do this with the replaceFirst() method which replaces
					// the first (and only) match with an empty string.
					s = m.replaceFirst("").trim();

					// Finally we break out of the loop over the token infos because we don’t need to check any other token types in this round.
					break;
				}
			}
			if (!match) {
				throw new MyParserException("Unexpected character in input: " + s);
			}
		}

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * PRIVATES CLASSES
	 */

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
	 * variable tokens but we need to also keep the name of the variable so that we can later use it to store or retrieve information.<br/>
	 * This class defines a number of static constants for the different types of tokens and a couple of fields that hold the data for the individual token
	 * 
	 * @author I310911
	 *
	 */
	public class Token {

		/*
		 * Define the different types of tokens
		 */
		public static final int EPSILON = 0;
		public static final int PLUSMINUS = 1;
		public static final int MULTDIV = 2;
		public static final int RAISED = 3;
		public static final int FUNCTION = 4;
		public static final int OPEN_BRACKET = 5;
		public static final int CLOSE_BRACKET = 6;
		public static final int NUMBER = 7;
		public static final int VARIABLE = 8;

		/*
		 * Hold data for individual token
		 */
		public final int token;
		public final String sequence;

		public Token(int token, String sequence) {
			super();
			this.token = token;
			this.sequence = sequence;
		}
	}

	/**
	 * 
	 * @author I310911
	 *
	 */
	public class Parser {
		LinkedList<Token> tokens;
		Token lookahead;

		/**
		 * 
		 * @param tokens
		 */
		public void parse(LinkedList<Token> tokens) {
			// we first create a shallow copy of the token list because we will be taking elements out of the list and we don’t want to create side effects on
			// the parameters.
			this.tokens =  (LinkedList<Token>) tokens.clone();
			lookahead = this.tokens.getFirst();

			expression();

			/*
			 * Once the expression has been parsed completely there should be no symbols left in the list. This means that the lookahead should be equal to
			 * Token.EPSILON. If there is still a symbol left in the lookahead it means that there is an error in the input.
			 */
			if (lookahead.token != Token.EPSILON)
				throw new MyParserException("Unexpected symbol "+lookahead.sequence+" found");
		}

		/**
		 * Utility method that reads the next token from the list.<br/>
		 * We pop the first token off the list and set the lookahead to the new head of the list.
		 */
		private void nextToken() {
			tokens.pop();
			// at the end of input we return an epsilon token
			if (tokens.isEmpty())
				lookahead = new Token(Token.EPSILON, "");
			else
				lookahead = tokens.getFirst();
		}

		/**
		 * Parse the non-terminal symbol expression<br/>
		 * The parser will have one method for every non-terminal symbol of the grammar
		 */
		private void expression() {
			// expression -> signed_term sum_op
			signedTerm();
			sumOp();
		}

		/**
 * 
 */
		private void sumOp() {
			if (lookahead.token == Token.PLUSMINUS) {
				// sum_op -> PLUSMINUS term sum_op
				nextToken();
				term();
				sumOp();
			} else {
				// sum_op -> EPSILON
			}
		}

		/**
		 * 
		 */
		private void signedTerm() {
			// If the next token is PLUSMINUS we can eat it up and then parse the non-terminal term. Otherwise we parse the non-terminal term directly.
			if (lookahead.token == Token.PLUSMINUS) {
				// signed_term -> PLUSMINUS term
				nextToken();
				term();
			} else {
				// signed_term -> term
				term();
			}
		}

		/**
 * 
 */
		private void term() {
			// term -> factor term_op
			factor();
			termOp();
		}

		/**
 * 
 */
		private void termOp() {
			if (lookahead.token == Token.MULTDIV) {
				// term_op -> MULTDIV factor term_op
				nextToken();
				signedFactor();
				termOp();
			} else {
				// term_op -> EPSILON
			}
		}

		/**
 * 
 */
		private void signedFactor() {
			if (lookahead.token == Token.PLUSMINUS) {
				// signed_factor -> PLUSMINUS factor
				nextToken();
				factor();
			} else {
				// signed_factor -> factor
				factor();
			}
		}

		/**
 * 
 */
		private void factor() {
			// factor -> argument factor_op
			argument();
			factorOp();
		}

		/**
 * 
 */
		private void factorOp() {
			if (lookahead.token == Token.RAISED) {
				// factor_op -> RAISED expression
				nextToken();
				signedFactor();
			} else {
				// factor_op -> EPSILON
			}
		}

		/**
 * 
 */
		private void argument() {
			if (lookahead.token == Token.FUNCTION) {
				// argument -> FUNCTION argument
				nextToken();
				argument();
			} else if (lookahead.token == Token.OPEN_BRACKET) {
				// argument -> OPEN_BRACKET sum CLOSE_BRACKET
				nextToken();
				expression();

				if (lookahead.token != Token.CLOSE_BRACKET)
					throw new MyParserException("Closing brackets expected and " + lookahead.sequence + " found instead");

				nextToken();
			} else {
				// argument -> value
				value();
			}
		}

		/**
 * 
 */
		private void value() {
			if (lookahead.token == Token.NUMBER) {
				// argument -> NUMBER
				nextToken();
			} else if (lookahead.token == Token.VARIABLE) {
				// argument -> VARIABLE
				nextToken();
			} else {
				throw new MyParserException("Unexpected symbol " + lookahead.sequence + " found");
			}
		}

	}

}
