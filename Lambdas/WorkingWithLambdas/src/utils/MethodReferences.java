package utils;

import java.util.function.Function;

public class MethodReferences<T> {

	@FunctionalInterface
	public interface StringFunction {
		String applyFunction(String s);
	}
	
	public static String transfrom(String s, StringFunction f) {
		return f.applyFunction(s);
	}
	
	public static <T,R> R genericTransfrom(T t, Function<T, R> f) {
		return f.apply(t);
	}
	
	public static String appendBang(String s) {
		return s + "!";
	}
	
	public static int addTen(int i) {
		return i + 10;
	}
	
}
