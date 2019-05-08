package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Employee {

	protected double salary;
	protected String name;

	public Employee(int salary, String name) {
		this.salary = salary;
		this.name = name;
	}

	@Override
	public String toString() {
		return name + ": " + salary;
	}
	
	public static class EmployeeFunctions {
		public static Predicate<Employee> earnsMoreThan10k = e -> e.salary > 10000;
		public static Predicate<Employee> earnsLessThan30k = e -> e.salary < 30000;
	}
	
	public static class EmployeeTestData {
		private static List<Employee> empList = new ArrayList<>();

		static {
			empList.add(new Employee(10000, "Bob"));
			empList.add(new Employee(20000, "Linda"));
			empList.add(new Employee(30000, "Jack"));
		}

		public static void giveRaise(Employee e, double percentage) {
			double sal = e.salary;
			e.salary = sal * (1 + (percentage / 100));
		}

		public static List<Employee> getList() {
			return empList;
		}
	}
}
