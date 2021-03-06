/*
 * This software and all files contained in it are distrubted under the MIT license.
 * 
 * Copyright (c) 2013 Cogito Learning Ltd
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package app.cogpar;

import app.cogpar.expressionnodes.EvaluationException;
import app.cogpar.expressionnodes.IExpressionNode;
import app.cogpar.expressionnodes.visitor.implementations.SetVariable;
import app.cogpar.parsercore.Parser;
import app.cogpar.parsercore.ParserException;

/**
 * Test the Parser
 */
public class Test {

	/**
	 * The main method to test the functionality of the parser
	 */
	public static void main(String[] args) {
		/*
		 * 2*(1+sin(pi/2))^2
		 * exp(1)
		 * ln(10)
		 * log(1000)
		 * (( 1 + 2 * 3 ) + ( 4 + 2 * 4 ) ) / ( x + 1 )
		 * -2+2
		 * -2-(-2)
		 */
		String exprstr = "-2-(-2)";
		if (args.length > 0)
			exprstr = args[0];

		Parser parser = new Parser();
		try {
			IExpressionNode expr = parser.parse(exprstr);
//			expr.accept(new SetVariable("pi", Math.PI));
			expr.accept(new SetVariable("x", 18));
			System.out.println("The value of the expression is " + expr.getValue());

		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (EvaluationException e) {
			System.out.println(e.getMessage());
		}
	}
}