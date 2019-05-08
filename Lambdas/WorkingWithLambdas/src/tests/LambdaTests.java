package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import tests.Employee.EmployeeTestData;
import utils.Transformer;
import utils.Matcher;
import utils.MethodReferences;
import utils.Sorting;

class LambdaTests {
	
	@Test
	void smallToBig() {	
		Sorting.sortSmallToBig(testString);
		assertEquals("two", testString[0]);
	}
	
	@Test
	void bigToSmall() {	
		Sorting.sortBigToSmall(testString);
		assertEquals("three", testString[0]);
	}
	
	@Test
	void sortFirstChar() {	
		Sorting.sortFirstChar(testString);
		assertEquals("two", testString[0]);
	}
	
	@Test
	void sortMethod() {	
		Arrays.sort(testString, Sorting::sortMethod);
		assertEquals("three", testString[0]);
	}
	
	@Test
	void betterElementString() {
		String best = Sorting.betterElement(testString[0], testString[1], (s1,s2) -> s1.length() > s2.length());
		assertEquals("four", best);
	}
	
	@Test
	void betterElementEmployee() {
		Employee best = 
				Sorting.betterElement(EmployeeTestData.getList().get(0), EmployeeTestData.getList().get(1), (e1,e2) -> e1.salary > e2.salary);
		assertEquals("Linda", best.name);
	}

	@Test
	void appendBang() {
		String result = MethodReferences.transfrom("Hello", MethodReferences::appendBang);
		assertEquals("Hello!", result);
	}
	
	@Test
	void genericAppendBang() {
		String result = MethodReferences.genericTransfrom("Hello", MethodReferences::appendBang);
		assertEquals("Hello!", result);
	}
	
	@Test
	void genericAddTen() {
		int result = MethodReferences.genericTransfrom(1, MethodReferences::addTen);
		assertEquals(11, result);
	}
	
	@Test
	void concatBang() {
		String result = MethodReferences.transfrom("Hello", "!"::concat);
		assertEquals("!Hello", result);
	}

	@Test
	void matchEmployee() {
		Employee emp = Matcher.getFirstMatch(EmployeeTestData.getList(), e -> e.salary > 22000);
		assertEquals("Jack", emp.name);
	}
	
	@Test
	void matchShortWords() {
		List<String> results = Matcher.getAllMatches(Arrays.asList(testString), w -> w.length() < 4);
		assertEquals("[two, one]", results.toString());
	}
	
	@Test
	void addBangToWords() {
		List<String> results = Transformer.transformList(Arrays.asList(testString), s -> s + "!");
		assertEquals("[four!, two!, one!, three!]", results.toString());
	}
	
	@Test
	void changeOh() {
		List<String> results = Transformer.transformList(Arrays.asList(testString), s -> s.replace("o", "oh"));
		assertEquals("[fohur, twoh, ohne, three]", results.toString());
	}
	
	@Test
	void wordLens() {
		List<Integer> results = Transformer.transformList(Arrays.asList(testString), s -> s.length());
		assertEquals("[4, 3, 3, 5]", results.toString());
	}
	
	@Test
	void higherOrderAnd() {
		
	}
	
	String [] testString = {"four","two", "one", "three"};
		
}
