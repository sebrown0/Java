/**
 * 
 */
package helpers;

import java.util.concurrent.Callable;

/**
 * @author Steve Brown
 *
 */
public class Employee implements Callable<Report>{
  private int empId;
  private Task task;
  
  public Employee(int empId) {
    this.empId = empId;
  }

  public int getEmpId() {
    return empId;
  }
  
  public void accept(Task t) {
    this.task = t;
  }

  @Override
  public Report call() throws Exception {
    boolean result = false;
    try {
      if(!task.getTaskName().equals("Task 4"))
          result = task.call();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Report(empId, task.getTaskName(), result, System.currentTimeMillis());
  }
}
