package tests;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.TimingUtils;

class TimingUtilsTest {

	@Test
	void test() {
		for (int i = 3; i < 8; i++) {
			int size = (int) Math.pow(10, i);
			System.out.printf("Sorting array of len %,d.%n", size);
			TimingUtils.timeOp(() -> sortArray(size));
		}
	}

	public static double[] randomNums(int len) {
		double [] nums = new double[len];
		for(int i=0; i<len;i++)
			nums[i] = Math.random();
		return nums;
	}
	
	public static void sortArray(int len) {
		double [] nums = randomNums(len);
		Arrays.sort(nums);
	}
}
