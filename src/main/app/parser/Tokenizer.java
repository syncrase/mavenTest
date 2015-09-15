package app.parser;

import java.util.LinkedList;
import java.util.regex.Matcher;
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
	 * Hold encountered tokens<br/>
	 * By storing the token information in a linked list, and always ensuring that we look for matches from the beginning of the list, we are giving precedence
	 * to patterns added first.
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
//		String s = new String(str);
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
				throw new ParserException("Unexpected character in input: " + s);
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