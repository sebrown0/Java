package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import main.helpers.Employee;
import main.helpers.Report;
import main.helpers.Task;

public class App {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    final int size = 10;
    List<Future<Report>> reports = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(20);
//    Map<Employee, Future<Report>> results = new HashMap<>();
    Map<Employee, Task> assignedTasks = new HashMap<>();
    Random rNum = new Random();

    for (int i = 1; i <= size; i++) {
      Employee e = new Employee(1 + rNum.nextInt(1000));
      Task t = new Task("Task " + i);
      assignedTasks.put(e, t);
      e.accept(t);
      Future<Report> f = executorService.submit(e);
      Thread.sleep(100);
      reports.add(f);
//      results.put(e, f);
      if (f.isDone()) {
//        results.put(e, f);
//        reports.add(f);
      }
    }
    prntReports(reports);
    shutdownExecutor(executorService);
  }

  private static void prntReports(List<Future<Report>> reports) {
    reports = sortReports(reports);
    reports.forEach(r -> {
      try {
        System.out.println(r.get());
      } catch (InterruptedException | ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
//    for (Future<Report> r : reports)
//      try {
//        prntReport(r.get());
//      } catch (InterruptedException | ExecutionException e) {
//        e.printStackTrace();
//      }
  }

  private static List<Future<Report>> sortReports(List<Future<Report>> reports) {
    Comparator<Future<Report>> completionTime = (r1, r2) -> {
      try {
        return (r1.get().getCompletedAt() > r2.get().getCompletedAt()) ? 1 : -1;
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
      return 0;};
    reports.sort(completionTime);
      
    return reports;
  }

  private static void shutdownExecutor(ExecutorService executorService) {
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
    }
  }
}
