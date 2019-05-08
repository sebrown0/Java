package lambda_utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Matcher {

	public static <T> List<T> allMatches(T [] l, Predicate<T> p){
		List<T> rList = new ArrayList<>();
		
		for (T t : Arrays.asList(l)) {
			if(p.test(t))
				rList.add(t);
		}
		return rList;
	}
	
	public static <T> T getFirstMatch(List<T> l, Predicate<T> pStr){
		for(T t: l) 
			if(pStr.test(t))
				return t;
		return null;
	}
	
	public static <T> List<T> getAllMatches(List<T> l, Predicate<T> pStr){
		List<T> results = new ArrayList<T>();
		for(T t: l) 
			if(pStr.test(t))
				results.add(t);
		return results;
	}

}
