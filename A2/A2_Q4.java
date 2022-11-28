import java.math.BigInteger;
import java.util.Scanner;

public class A2_Q4 {

	public static String mod_fibonacci(int N, BigInteger K) {
		BigInteger[] fibsToN = fib(N);

		return findXY(N, K, fibsToN);
	}

	public static BigInteger[] fib(int N){
		BigInteger[] myFibs = new BigInteger[N+1];
		if(N==1){
			myFibs[0] = BigInteger.valueOf(0);
			myFibs[1] = BigInteger.valueOf(1);
			return myFibs;
		}
		myFibs[0] = BigInteger.valueOf(0);
		myFibs[1] = BigInteger.valueOf(1);
		myFibs[2] = BigInteger.valueOf(1);
		for(int i = 3; i <= N; i++){
			myFibs[i] = myFibs[i-2].add(myFibs[i-1]);
		}
		return myFibs;
	}

	public static String findXY(int N, BigInteger K, BigInteger[] fibs){
		if(N==1){
			return "X";
		}
		else if(N==2){
			return "Y";
		}
		else if(N == 3){
			if(K.equals(BigInteger.valueOf(1))){
				return "X";
			}
			else if(K.equals(BigInteger.valueOf(2))){
				return "Y";
			}
		}
		else if(N==4){
			if(K.equals(BigInteger.valueOf(1))){
				return "Y";
			}
			else if(K.equals(BigInteger.valueOf(2))){
				return "X";
			}
			else if(K.equals(BigInteger.valueOf(3))){
				return "Y";
			}
		}
		int first = N-2;
		int second = N-1;
		if(K.compareTo(fibs[first]) == 1 ){ //greater than
			//key in second part
			BigInteger newKey = K.subtract(fibs[first]);
			int newN = second;
			return findXY(newN, newKey, fibs);
		}
		else if(K.compareTo(fibs[first]) == -1 ){
			int newN = first;
			return findXY(newN, K, fibs);
		}

		else{
			//K==fibs(first)
			return "Y";
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(mod_fibonacci(7,BigInteger.valueOf(7)));
		//System.out.println(mod_fibonacci(3, BigInteger.valueOf(2)));
		System.out.println(mod_fibonacci(3, new BigInteger("2")));
	}
}
