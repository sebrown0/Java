package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Employee {

	protected double salary;
	protected String name;
	protected int id;

	public Employee(int salary, String name, int id) {
		this.salary = salary;
		this.name = name;
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
	public String toString() {
		return id + ": " + name + ": " + salary;
	}
	
	public static class EmployeeFunctions {
		public static Predicate<Employee> earnsMoreThan10k = e -> e.salary > 10000;
		public static Predicate<Employee> earnsLessThan30k = e -> e.salary < 30000;
	}
	
	public static class EmployeeTestData {
		private static List<Employee> empList = new ArrayList<>();
		private static Map<Integer, Employee> empMap = new HashMap<>();

		static {
			empList.add(new Employee(10000, "Bob", 1));
			empList.add(new Employee(20000, "Linda", 2));
			empList.add(new Employee(30000, "Jack", 3));
			
			empList.stream().forEach(e -> empMap.put(e.getId(), e));
		}

		public static void giveRaise(Employee e, double percentage) {
			double sal = e.salary;
			e.salary = sal * (1 + (percentage / 100));
		}

		public static List<Employee> getEmpList() {
			return empList;
		}
		
		public static Map<Integer, Employee> getEmpMap(){
		  return empMap;
		}
	}
}
