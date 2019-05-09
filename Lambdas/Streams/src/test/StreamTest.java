package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import stream_utils.StreamUtils;
import test.Employee.EmployeeTestData;

class StreamTest {

  @Test
  void giveRaiseAndPrint() {
    EmployeeTestData.getEmpList().stream().forEach(StreamUtils.giveRaiseAndPrint);
  }

  @Test
  void squareNumbers() {
    TestData testData = new TestData();
    Double[] nums = testData.getTestNums();
    Double[] sqrNums = Stream.of(nums).map(n -> n * n).toArray(Double[]::new);
    assertEquals(1.0, (double) sqrNums[0]);
    assertEquals(9.0, (double) sqrNums[2]);
  }

  @Test
  void filter1() {
    Integer [] ids = {1,2,3,4,5};
    List<Employee> eList = Stream.of(ids)
        .map(StreamUtils::empById)
        .filter(e -> e != null)
        .filter(e -> e.salary < 31000.0)
        .filter(e -> e.name.equals("Linda"))
        .collect(Collectors.toList());
    assertEquals(2, eList.get(0).getId());
  }
  
  @Test
  void filter2() {
    Integer [] ids = {1,2,3,4,5};
    Employee emp = Stream.of(ids)
        .map(StreamUtils.findEmpWithId)
        .filter(StreamUtils.notNull)
        .filter(StreamUtils.salLessThan31K)
        .filter(StreamUtils.empNameIsLinda)
        .findFirst()
        .orElse(null);
    assertEquals(2, emp.getId());
  }

  @Test
  void checkOrElse() {
    List<String> l = Arrays.asList("one", "two", "three", "four");
    String res = l.stream()
        .map(String::toUpperCase)
        .filter(s -> s.equals("one"))
        .findFirst()
        .orElse("None");
    assertEquals("None", res);
  }
}
