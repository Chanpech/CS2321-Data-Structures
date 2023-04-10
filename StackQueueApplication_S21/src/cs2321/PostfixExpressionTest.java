package cs2321;
/**
 * @author Chanpech Hoeng
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PostfixExpressionTest {
	
	@Before
	public void setUp() throws Exception {
	}
	//Test 3 + 5
	@Test
	public void testEvaluate1() {
		String test = "3 5 +";
		int expected = 8;
		org.junit.Assert.assertEquals("evaluate() is 3 5 +", expected , PostfixExpression.evaluate(test));
	}
	//Test (3-4)*5
	@Test
	public void testEvaluate2() {
		String test = "3 4 - 5 *";
		int expected = -5;
		org.junit.Assert.assertEquals("evaluate() is 3 4 - 5 *", expected , PostfixExpression.evaluate(test));
	}
	//Test 20/5
	@Test
	public void testEvaluate3() {
		String test = "20 5 /";
		int expected = 4;
		org.junit.Assert.assertEquals("evaluate() is 20 5 /", expected , PostfixExpression.evaluate(test));
	}
	//Test 9 * 9
	@Test
	public void testEvaluate4() {
		String test = "9 9 *";
		int expected = 81;
		org.junit.Assert.assertEquals("evaluate() is 9 9 *", expected , PostfixExpression.evaluate(test));
	}
	
	@Test
	public void testEvaluate5() {
		String test = "5 20 5 2 * + 3 / -";
		int expected = -5;
		org.junit.Assert.assertEquals("evaluate() is 5 20 5 2 * + 3 / -", expected , PostfixExpression.evaluate(test));
	}
	//Test (1-5)* (-1)
	@Test
	public void testEvaluate6() {
		String test = "1 5 - -1 *";
		int expected = 4;
		org.junit.Assert.assertEquals("evaluate() is 1 5 - -1 *", expected , PostfixExpression.evaluate(test));
	}
	//Test (5/2)
	@Test
	public void testEvaluate7() {
		String test = "5 2 /";
		int expected = 2;
		org.junit.Assert.assertEquals("evaluate() is 5 2 /", expected , PostfixExpression.evaluate(test));
	}
	//Test (0 * 3) + 5
	@Test
	public void testEvaluate8() {
		String test = "0 3 * 5 +";
		int expected = 5;
		org.junit.Assert.assertEquals("evaluate() is 0 3 * 5 +", expected , PostfixExpression.evaluate(test));
	}

}
