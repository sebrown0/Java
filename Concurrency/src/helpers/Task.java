
package helpers;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author Steve Brown
 *
 */
public class Task implements Callable<Boolean>{
  private String taskName;
    
  public Task(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskName() {
    return taskName;
  }

  @Override
  public Boolean call() throws Exception {
    doSomeWork();
    return true;
  }
  
  private void doSomeWork() {
    int sleep = 10_000 + new Random().nextInt(1_000_000);
    int i = 0;
    
    while(i < sleep) {
      for(int j = 0; j < 100_000; j++) 
        for(int k = 0; k < 10_000; k++)
          for(int l = 0; l < 10_000; l++)
      i++;
    }
  }
}
