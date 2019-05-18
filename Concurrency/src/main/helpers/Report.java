
package main.helpers;

/**
 * @author Steve Brown
 *
 */
public class Report {
  private int empId;
  private String task;
  private boolean taskComplete = false;
  private long completedAt;
  
  public Report(int empId, String task, boolean taskComplete, long completedAt) {
    super();
    this.empId = empId;
    this.task = task;
    this.taskComplete = taskComplete;
    this.completedAt = completedAt;
  }
  
  public long getCompletedAt() {
    return completedAt;
  }

  public int getEmpId() {
    return empId;
  }
  
  public String getTask() {
    return task;
  }

  public boolean isTaskComplete() {
    return taskComplete;
  }

  public void setTaskComplete(boolean taskComplete) {
    this.taskComplete = taskComplete;
  }
 
  @Override
  public String toString() {
    String s = String.format("Emp ID =  %d : Task = %s : Task Complete = %b : At Time = %d", getEmpId(), getTask(), isTaskComplete(), getCompletedAt());
    return s;
  }
}
