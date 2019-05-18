package main;

import java.util.Arrays;

public class Sorting {
	
	public interface TwoElementPredicate<T> {
		boolean elementPredicate(T t1, T t2);
	}
	
	@FunctionalInterface
	public interface sortSmallestFirst {
		int sortFunction(String s1, String s2);
	}

	public static int sortFunction(String s1, String s2) {
		return (s1.length() > s2.length()) ? 1 : -1;
	}
	
	public static String[] sortSmallToBig(String[] s) {
		Arrays.sort(s, (s1, s2) -> s1.length() - s2.length());
		return s;
	}

	public static String[] sortBigToSmall(String[] s) {
		Arrays.sort(s, (s1, s2) -> s2.length() - s1.length());
		return s;
	}

	public static String[] sortFirstChar(String[] s) {
		Arrays.sort(s, (s1, s2) -> s2.charAt(0) - s1.charAt(0));
		return s;
	}

	public static int sortMethod(String s1, String s2) {
		return s2.length() - s1.length();
	}

	public static <T> T betterElement(T t1, T t2, TwoElementPredicate<T> ePredicate) {
		return (ePredicate.elementPredicate(t1, t2) ? t1 : t2);
	}
}
