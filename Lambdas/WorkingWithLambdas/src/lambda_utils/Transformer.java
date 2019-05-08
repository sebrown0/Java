package lambda_utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Transformer {

	public static <T, R> R transformArg(T arg, Function<T, R> func) {
		return func.apply(arg);
	}
	
	@SafeVarargs
	public static <T> T transformArg(T arg, Function<T, T>... funcs) {
		Function<T, T> composedFunction = composeAll(funcs);
		return (transformArg(arg, composedFunction));
	}
	
	public static <T, R> List<R> transformList(List<T> l, Function<T, R> func) {
		List<R> results = new ArrayList<>();
		for (T t : l)
			results.add(func.apply(t));
		return results;
	}
	
	@SafeVarargs
	public static <T> List<T> transformList(List<T> l, Function<T, T>... transformers) {
		Function<T, T> composedFunction = composeAll(transformers);
		return (transformList(l, composedFunction));
	}	
	
	@SafeVarargs
	public static <T> Function<T, T> composeAll(Function<T, T>... funcs){
		Function<T, T> result = Function.identity();
		for (Function<T, T> f : funcs) 
			result = result.compose(f);
		return result;
	}
	
	public static <T> void processArg(T arg, Consumer<T> operation) {
		operation.accept(arg);
	}
	
	public static <T> void processList(List<T> l, Consumer<T> operation) {
		for (T t : l) 
			operation.accept(t);
	}
}
