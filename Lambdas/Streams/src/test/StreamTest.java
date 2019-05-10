package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.DoubleUnaryOperator;

import org.junit.jupiter.api.Test;

import stream_utils.StreamUtils;
import timer.Operation;
import timer.TimingUtils;

class StreamTest {

//  @Test
//  void giveRaiseAndPrint() {
//    EmployeeTestData.getEmpList().stream().forEach(StreamUtils.giveRaiseAndPrint);
//  }
//
//  @Test
//  void squareNumbers() {
//    TestData testData = new TestData();
//    Double[] nums = testData.getTestNums();
//    Double[] sqrNums = Stream.of(nums).map(n -> n * n).toArray(Double[]::new);
//    assertEquals(1.0, (double) sqrNums[0]);
//    assertEquals(9.0, (double) sqrNums[2]);
//  }
//
//  @Test
//  void filter1() {
//    List<Employee> eList = Stream.of(TestData.getIds())
//        .map(StreamUtils::empById)
//        .filter(e -> e != null)
//        .filter(e -> e.salary < 31000.0)
//        .filter(e -> e.name.equals("Linda"))
//        .collect(Collectors.toList());
//    assertEquals(2, eList.get(0).getId());
//  }
//  
//  @Test
//  void filter2() {
//    Employee emp = Stream.of(TestData.getIds())
//        .map(StreamUtils.findEmpWithId)
//        .filter(StreamUtils.notNull)
//        .filter(StreamUtils.salLessThan31K)
//        .filter(StreamUtils.empNameIsLinda)
//        .findFirst()
//        .orElse(null);
//    assertEquals(2, emp.getId());
//  }
//
//  @Test
//  void checkOrElse() {
//    String res = TestData.getListString().stream()
//        .map(String::toUpperCase)
//        .filter(s -> s.equals("one"))
//        .findFirst()
//        .orElse("None");
//    assertEquals("None", res);
//  }

//  @Test
//  void reduce1() {
//    String res =  TestData.getListString().stream().reduce("", String::concat);
//    assertEquals("onetwothreefour", res);
//  }
//  
//    @Test
//    void mapAndReduce() {
//      String res =  TestData.getListString().stream().map(String::toUpperCase).reduce("", String::concat);
//      assertEquals("ONETWOTHREEFOUR", res);
//    }
//    
//    @Test
//    void reduce2() {
//      String res =  TestData.getListString().stream().reduce((s1, s2) -> {return s1 + "," + s2;}).get();
//      assertEquals("one,two,three,four", res);
//    }
//    
//    @Test
//    void sum1() {
//      int res =  TestData.getListString().stream().mapToInt(s -> s.length()).sum();
//      assertEquals(15, res);
//    }
//  
//    @Test
//    void filter3() {
//      String res =  TestData.getListString().stream().filter(s -> s.contains("r")).findFirst().orElse("none");
//      assertEquals("three", res);
//    }
//  
//    @Test
//    void partition1() {
//      Map<Boolean, List<Employee>> emps = 
//          EmployeeTestData.getEmpList().stream()
//          .collect(Collectors.partitioningBy(e -> e.getId() == 3));
//      assertEquals(3, emps.get(true).get(0).getId());
//    }
//    
//    @Test
//    void groupBy1() {
//      Map<String, List<Employee>> emps = 
//          EmployeeTestData.getEmpList().stream()
//          .collect(Collectors.groupingBy(e -> e.getDept()));
//      assertEquals("Jack", emps.get("Accounts").get(0).getName());
//    }

    @Test
    void timingParallelSerial() {
      DoubleUnaryOperator op = d -> {
        for(int i = 0; i < 1000; i++) 
          d = d * 1.21;
        return d;
        };
      Operation serialOp = () -> StreamUtils.operateOnDoublesSerialy(TestData.getRndNums(), op);
      Operation parallelOp = () -> StreamUtils.operateOnDoublesInParallel(TestData.getRndNums(), op);
      
      double serialSecs = TimingUtils.timeOp(serialOp);
      double parallelSecs = TimingUtils.timeOp(parallelOp);
      assertTrue(serialSecs > parallelSecs);
    }

}
