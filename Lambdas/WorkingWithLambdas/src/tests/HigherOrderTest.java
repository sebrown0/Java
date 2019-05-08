package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import tests.Employee.EmployeeFunctions;
import tests.Employee.EmployeeTestData;
import utils.Matcher;
import utils.Transformer;

class HigherOrderTest {
	
	Function<Double, Double> timesTwo = n -> n * 2;
	Function<Double, Double> addTen = n -> n + 10;
	Function<Double, Predicate<Employee>> empsWhoEarnAboveThreshold =
			threshold -> (e -> e.salary > threshold);
			
	Consumer<Employee> giveRaise = e -> EmployeeTestData.giveRaise(e, 10);

	@Test
	void withPredicateAnd() {
		List<Employee> result = Matcher.getAllMatches(EmployeeTestData.getList(),
				EmployeeFunctions.earnsMoreThan10k.and(EmployeeFunctions.earnsLessThan30k));
		assertEquals("Linda", result.get(0).name);
	}
	
	@Test
	void withPredicateAndArgument() {
		List<Employee> result = Matcher.getAllMatches(EmployeeTestData.getList(),
				empsWhoEarnAboveThreshold.apply(10000.0));
		assertEquals("Linda", result.get(0).name);
	}

	@Test
	void withFunctionAndThen() {
		assertEquals(14.0, (double) Transformer.transformArg(2.0, timesTwo.andThen(addTen)));
	}

	@Test
	void withFunctionCompose() {
		assertEquals(14.0, (double) Transformer.transformArg(2.0, addTen, timesTwo));
		assertEquals(18.0, (double) Transformer.transformArg(2.0, addTen, timesTwo, timesTwo));
		assertEquals(38.0, (double) Transformer.transformArg(2.0, addTen, timesTwo, addTen, timesTwo));
	}
	
	@Test
	void withProcessArgWithConsumer() {
		Employee emp = EmployeeTestData.getList().get(2);
		Transformer.processArg(emp, giveRaise.andThen(System.out::println));
		assertEquals(33000.0, emp.salary);
	}
	
	@Test
	void withProcessListWithConsumer() {
		Transformer.processList(EmployeeTestData.getList(), giveRaise);
		assertEquals(11000.0, EmployeeTestData.getList().get(0).salary);
		assertEquals(22000.0, EmployeeTestData.getList().get(1).salary);
		assertEquals(36300.0, EmployeeTestData.getList().get(2).salary);
	}
	
}
