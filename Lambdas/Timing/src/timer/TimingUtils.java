package timer;

public class TimingUtils {
	private static final double ONE_BILLION = 1_000_000_000;

	public static void timeOp(Operation operation) {
		long startTime = System.nanoTime();
		operation.runOperation();
		long endTime = System.nanoTime();
		double elapsedSeconds = (endTime - startTime) / ONE_BILLION;
		System.out.printf(" Elapsed time: %.3f seconds. %n", elapsedSeconds);
	}
}
