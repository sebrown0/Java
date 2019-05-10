package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Employee {

	protected double salary;
	protected int id;
	protected String name;
	protected String dept;

	public Employee(int salary, String name, int id, String dept) {
		this.salary = salary;
		this.name = name;
		this.id = id;
		this.dept = dept;
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

	public int getId() {
    return id;
  }
	
	public String getDept() {
	  return dept;
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
			empList.add(new Employee(10000, "Bob", 1,"HR"));
			empList.add(new Employee(20000, "Linda", 2,"HR"));
			empList.add(new Employee(30000, "Jack", 3, "Accounts"));
			
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
