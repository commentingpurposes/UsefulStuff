/*
 * These are just a bunch of methods (mostly simple) that might come in handy every now and then.
 * I have them all in this one class for convenience purposes.
 * Feel free to suggest and/or add more methods if you think they can be useful.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class UsefulMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * List<Integer> l = new ArrayList<Integer>(); l.add(5); l.add(4);
		 * l.add(34); l.add(1); l.add(33); printList(l); bubbleSort(l);
		 * printList(l);
		 * 
		 * System.out.println(factorial(6));
		 * 
		 * pascalsTriangle(5);
		 * 
		 * List<String> l = new ArrayList<String>(); l.add("Saliou");
		 * l.add("Sup"); l.add("neyney");
		 * 
		 * binarySearch(l, "Sup");
		 * 
		 * expressionValidity("[()()][");
		 * 
		 * int[][] m = {{1,2},{0,1},{4,3}};
		 * 
		 * m = transposeMatrix(m); for(int[] a : m){ for(int b : a)
		 * System.out.print(b + " "); System.out.println(); }
		 * 
		 * System.out.println(gcd(100,12));
		 * 
		 * System.out.println(numCombinations("ABadsfgfxdC", 4));
		 * 
		 * primeValidity(83);
		 */
		List<Integer> l = new ArrayList<Integer>();
		l.add(5);
		l.add(4);
		l.add(34);
		l.add(5);
		l.add(1);
		l.add(33);
		printList(l);
		bucketSort(l);
	}

	static int numCombinations(String s, int r) {
		int num = 0;
		int n = s.length();
		num = (factorial(n) / (factorial(r) * (factorial(n - r))));

		return num;
	}

	static int numPermutations(String s, int r) {
		int num = 0;
		int n = s.length();
		num = (factorial(n) / (factorial(n - r)));

		return num;
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	static int[][] transposeMatrix(int[][] matrix) {
		int numCols = matrix.length;
		int numRows = matrix[0].length;
		int[][] transpose = new int[numRows][numCols];
		for (int row = 0; row < numCols; row++)
			for (int col = 0; col < numRows; col++) {
				transpose[col][row] = matrix[row][col];
			}
		matrix = transpose;

		return matrix;
	}

	static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
	}

	static void primeValidity(int n) {
		boolean valid = true;
		int divisor = 0;
		for (int i = 2; i <= n / 2; i++)
			if (n % i == 0) {
				divisor = i;
				valid = false;
				break;
			}

		if (valid)
			System.out.println("Your number is a valid prime number.");
		else
			System.out.println("Your number is not a valid prime number."
					+ " It is divisible by " + divisor + ".");
	}

	static void expressionValidity(String s) {
		Stack<Character> stack = new Stack<Character>();
		boolean error = false;
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '[')
				stack.push(c);
			else if (c == ')' || c == ']') {
				if (stack.isEmpty()) {
					System.out.println("The expression " + s
							+ " is invalid. Not enough left parentheses.");
					error = true;
					break;
				} else {
					char pop = (char) stack.pop();
					if (c == ')' && pop != '(' || c == ']' && pop != '[') {
						System.out.println("The expression " + s
								+ " is invalid. Mismatched parentheses.");
						error = true;
						break;
					}
				}
			}
		}
		if (!error) {
			if (stack.isEmpty())
				System.out.println("The expression is valid!");
			else
				System.out.println("The expression " + s
						+ " is invalid. Too many left parentheses.");
		}
	}

	static void pascalsTriangle(int n) {
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == n)
					a[i][j] = 1;
				else
					a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
			}
		}
		printList(a);
	}

	static void bubbleSort(List<Integer> l) {
		boolean swapped = true;
		for (int i = l.size() - 1; swapped && i >= 1; i--) {
			swapped = false;
			for (int j = 0; j < i; j++) {
				if (l.get(j) > l.get(j + 1)) {
					Integer temp = l.get(j);
					l.set(j, l.get(j + 1));
					l.set(j + 1, temp);
					swapped = true;
				}
			}
		}
		System.out.println(l + " is sorted using bubble sort.");
	}

	static void insertionSort(List<Integer> l) {
		for (int i = 1; i < l.size(); i++) {
			int j = i;
			while (j > 0 && l.get(j - 1) > l.get(j)) {
				int temp = l.get(j - 1);
				l.set(j - 1, l.get(j));
				l.set(j, temp);
				j--;
			}
		}
		System.out.println(l + " is sorted using insertion sort.");
	}

	static void selectionSort(List<Integer> l) {
		for (int i = 0; i < l.size() - 1; i++) {
			int min = i;
			for (int j = i; j < l.size(); j++) {
				if (l.get(j) < l.get(min)) {
					min = j;
				}
			}
			int temp = l.get(i);
			l.set(i, l.get(min));
			l.set(min, temp);
		}
		System.out.println(l + " is sorted using insertion sort.");
	}
	
	static void bucketSort(List<Integer> l){
		int max = Collections.max(l);
		int[] arr = new int[max+1];
		int[] counters = new int[max+1];
		for(int i = 0; i < l.size(); i++){
			arr[l.get(i)] = l.get(i);
			counters[l.get(i)]++;
		}
		List<Integer> newList = new ArrayList<Integer>();
		for(int i : arr){
			while(counters[i] > 0){
				newList.add(arr[i]);
				counters[i]--;
			}
		}
		l = newList;
		System.out.println(l + " is sorted using bucket sort.");
	}

	static void binarySearch(List<Integer> l, int item) {
		bubbleSort(l);
		int a = 0;
		int b = l.size();
		while (a < b) {
			int m = (a + b) / 2;
			if (item <= l.get(m))
				b = m;
			else
				a = m + 1;
		}
		if (item == l.get(a))
			System.out.println(item + " is at index " + a + " in the list " + l
					+ ".");
		else
			System.out.println(item + " is not in the list " + l + ".");
	}

	static void linearSearch(List<String> l, String item) {
		boolean found = false;
		for (String element : l) {
			if (element.equals(item)) {
				System.out.println("Found " + item + " at index "
						+ l.indexOf(element) + ".");
				found = true;
			}
		}
		if (!found)
			System.out.println("Did not find " + item + " in " + l + ".");
	}

	static void towersOfHanoi(int n, int x, int y, int z) {
		if (n > 0) {
			towersOfHanoi(n - 1, x, z, y);
			System.out.println("Move ring " + n + " from " + x + " to " + y
					+ ".");
			towersOfHanoi(n - 1, z, y, x);
		}
	}

	static int fibonacci(int n) {
		int result = 0;

		if (n == 0 || n == 1)
			result = 1;
		else
			result = fibonacci(n - 1) + fibonacci(n - 2);

		return result;
	}

	static int factorial(int n) {
		if (n < 0)
			return -1;
		else {
			if (n == 0 || n == 1)
				return 1;
			else
				return n * factorial(n - 1);
		}
	}

	static int sumPowersOf2(int n) {
		int x = 1;
		int p = 1;

		for (int i = 1; i <= n; i++) {
			p *= 2;
			x += p;
		}

		return x;
	}

	static void printList(List<Integer> l) {
		for (Integer i : l)
			System.out.print(i + " ");
		System.out.println();
	}

	static void printList(int[][] a) {
		for (int[] i : a) {
			for (int j : i)
				System.out.print(j + " ");
			System.out.println();
		}
		System.out.println();
	}
}
