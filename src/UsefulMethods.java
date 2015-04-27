/*
 * These are just a bunch of methods (mostly simple) that might come in handy every now and then.
 * I have them all in this one class for convenience purposes.
 * Feel free to suggest and/or add more methods if you think they can be useful.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class UsefulMethods {

	public static void main(String[] args) {
		
		List<Integer> l = new ArrayList<Integer>();
		l.add(5);
		l.add(12);
		l.add(3);
		l.add(7);
		l.add(4);
		l.add(23);
		l.add(3);
		l.add(-3);
		l.add(24);
		l.add(12);
		l.add(56);
		l.add(34);
		l.add(35);
		l.add(12);
		//shellSort(l);
		int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
		System.out.println(clockwiseRotatedMatrix(a));
		
		//NEED TO FIX SHELL, HEAP, AND QUICK SORTS
		//
		//NEED TO FIX SHORTEST PATH METHOD
	}

	/*
	 * Find whether there is an error or not after crc method given a word and a bias modulo 2 divisor.
	 */
	static boolean crcError(int word, int bias) {
		if (Integer.parseInt(moduloTwoDivisionRemainder(word, bias)) != 0)
			return true;
		else
			return false;
	}

	/*
	 * Takes data bits and bias and returns sent word using modulo 2 division remainder.
	 * This remainder is appended to the original data bits; the new expression is the sent word.
	 */
	static int crc(int data, int bias) {
		int c = Integer.toString(bias).length();
		String newData = Integer.toString(data);
		
		/*
		 * Let n be the number of bits in 'bias', append n-1 '0' bits to the data before performing mod 2 division.
		 */
		for (int i = 0; i < c - 1; i++)
			newData += "0";
		int dividend = Integer.parseInt(newData);
		
		String remainder = moduloTwoDivisionRemainder(dividend, bias);

		/*
		 * Append obtained result after mod 2 division to original data bits, then return.
		 */
		String sentData = Integer.toString(data) + remainder;
		int result = Integer.parseInt(sentData);

		return result;
	}

	/*
	 * This method performs mod 2 division.
	 * First parameter it takes is the dividend; the second is the divisor.
	 */
	static String moduloTwoDivisionRemainder(int word, int bias) {
		int biasLength = Integer.toString(bias).length();
		int wordLength = Integer.toString(word).length();
		String w = Integer.toString(word);
		
		/*
		 * It would not make sense for the divisor to have more bits than the dividend.
		 */
		if (w.length() < biasLength) {
			System.out.println("Invalid .. Bias cannot be longer than word.");
			return null;
		}

		//RESUME COMMENTS FROM HERE
		int counter = 0;
		int co = 0;
		ArrayList<Character> r = new ArrayList<Character>();
		String current = w.substring(0, biasLength);
		for (char c : current.toCharArray()) {
			r.add(c);
			co++;
		}

		
		while (counter < wordLength - biasLength) {
			ArrayList<Character> a = new ArrayList<Character>(r);
			if (a.get(0) != '0') {
				for (int i = biasLength - 1; i > 0; i--) {
					if (a.get(i) == Integer.toString(bias).charAt(i)) {
						r.set(i - 1, '0');
					} else {
						r.set(i - 1, '1');
					}
				}
			} else {
				for (int i = biasLength - 1; i > 0; i--) {
					r.set(i - 1, a.get(i));
				}
			}
			r.set(biasLength - 1, w.charAt(co));
			co++;
			counter++;
		}
		String remainder = "";
		for (int i = 1; i < r.size(); i++)
			remainder += r.get(i);

		return remainder;

	}

	static void shortestPath(int x, int y) throws FileNotFoundException {
		File file = new File("adjacency_matrix.txt");
		Scanner scan = new Scanner(file);

		int numNodes = scan.nextInt();
		int[][] adjMatrix = new int[numNodes][numNodes];
		while (scan.hasNextLine()) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			int weight = scan.nextInt();
			adjMatrix[start][end] = weight;
			adjMatrix[start][end] = weight;
		}
	}

	static void getShortestPath(int[][] matrix, int idx1, int idx2,
			int destination) {
		int shortest = 0;
		if (matrix[idx1][idx2] != 0) {
			shortest += matrix[idx1][idx2];
			while (idx2 != destination) {
				// get unvisited adjacent nodes

			}
		}
	}

	/*
	 * This is a method that will return the longest palindrome found within a given string.
	 */
	static void longestPalindrome(String s) {
		String palindrome = "";
		for (int i = 0; i < s.length(); i++) {
			System.out.println(i);
			int j = 1;
			while (i + j < s.length() && s.charAt(i + j) == s.charAt(i)) {
				if (j + 1 >= palindrome.length()) {
					palindrome = s.substring(i, i + j + 1);
				}
				j++;
			}
			int c = 1;
			while (i - c >= 0 && i + j - 1 < s.length()) {
				if (i + j + 1 < s.length()
						&& isPalindrome(s.substring(i - c, i + j + 1))
						&& c + j + 2 >= palindrome.length()) {
					palindrome = s.substring(i - c, i + j + 1);
				} else if (isPalindrome(s.substring(i - c))
						&& c + j + 2 >= palindrome.length()) {
					palindrome = s.substring(i - c, i + j + 1);
				}
				c++;
				j++;
			}
		}
		System.out.println("The longest palindrome within the given word (" + s
				+ ") is: " + palindrome + ".");
	}

	/*
	 * Find whether or not a given string is a palindrome.
	 */
	public static boolean isPalindrome(String s) {
		boolean result = false;
		String r = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			r += s.charAt(i);
		}
		if (s.equals(r))
			result = true;
		return result;
	}

	/*
	 * This method will combine sorted arrays.
	 * This means that it will take two sorted arrays; then it will combine and sort them.
	 * It results in a longer, but still sorted array.
	 */
	static int[] combineSortedArrays(int[] A, int[] B) {
		int[] result = new int[A.length + B.length];
		int i = 0;
		int j = 0;
		int counter = 0;
		while (i < A.length && j < B.length) {
			if (A[i] <= B[j]) {
				result[counter] = A[i];
				counter++;
				i++;
			} else {
				result[counter] = B[j];
				counter++;
				j++;
			}
		}
		while (i < A.length) {
			result[counter] = A[i];
			counter++;
			i++;
		}
		while (j < B.length) {
			result[counter] = B[j];
			counter++;
			j++;
		}
		printArray(result);
		return result;
	}

	/*
	 * This method will combine sorted lists.
	 * This means that it will take two sorted lists; then it will combine and sort them.
	 * It results in a longer, but still sorted list.
	 */
	static ArrayList<Integer> combineSortedLists(List<Integer> A,
			List<Integer> B) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		while (i < A.size() && j < B.size()) {
			if (A.get(i) <= B.get(j)) {
				result.add(A.get(i));
				i++;
			} else {
				result.add(B.get(j));
				j++;
			}
		}
		while (i < A.size()) {
			result.add(A.get(i));
			i++;
		}
		while (j < B.size()) {
			result.add(B.get(j));
			j++;
		}
		System.out.println(result);
		return result;
	}

	/*
	 * Rotate a 2D matrix clockwise.
	 * Example:
	 * 		1	2	3		=>		7	4	1
	 * 		4	5	6		=>		8	5	2
	 * 		7	8	9		=>		9	6	3
	 */
	static int[][] clockwiseRotatedMatrix(int[][] matrix) {
		int size = matrix.length;
		int result[][] = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				result[i][j] = matrix[size - j - 1][i];
		for (int[] a : result) {
			for (int b : a)
				System.out.print(b + " ");
			System.out.println();
		}
		return result;
	}

	/*
	 * This method determines how many combinations are possible given a total number of characters
	 * and how many characters is wanted for each combination.
	 * Combination is different from permutation in that different combinations cannot comprise of only the same characters.
	 */
	static int numCombinations(String s, int r) {
		int num = 0;
		int n = s.length();
		num = (factorial(n) / (factorial(r) * (factorial(n - r))));

		return num;
	}

	/*
	 * This method determines how many permutations are possible given a total number of characters
	 * and how many characters is wanted for each permutation.
	 * Permutation is different from combination in that different permutations can comprise of the same characters
	 * which are not in the same order.
	 */
	static int numPermutations(String s, int r) {
		int num = 0;
		int n = s.length();
		num = (factorial(n) / (factorial(n - r)));

		return num;
	}

	/*
	 * This method determines the greatest common denominator between two numbers.
	 */
	static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	/*
	 * This method will return the transpose of a given 2D matrix.
	 */
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

	/*
	 * The following simple method is to illustrate the swapping of two items.
	 */
	static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
	}

	/*
	 * The following method checks whether or not a given number is a prime number.
	 */
	static void primeValidity(int n) {
		boolean valid = true;
		int divisor = 0;
		for (int i = 2; i <= Math.sqrt(n); i++)
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

	/*
	 * This method checks for the validity of a given string.
	 * It checks whther or not the parentheses and brackets come in pairs and are at the right place.
	 */
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

	/*
	 * This method will output Pascal's Triangle up to the nth line (n is provided).
	 */
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
		print2DArray(a);
	}

	/*
	 * This method merges two lists; it simply uses the method to merge lists above.
	 */
	static ArrayList<Integer> merge(List<Integer> a, List<Integer> b) {
		return combineSortedLists(a, b);
	}

	/*
	 * Sotr list using merge sort.
	 */
	static List<Integer> mergeSort(List<Integer> l) {
		if (l.size() == 1) {
			return l;
		} else {
			int middle = l.size() / 2;
			List<Integer> left = mergeSort(l.subList(0, middle));
			List<Integer> right = mergeSort(l.subList(middle, l.size()));
			ArrayList<Integer> result = merge(left, right);
			System.out.println(result);
			return result;
		}
	}
	
	/*
	 * Find the median of three of an array, the three numbers being the ones at first, middle, and last indices of the array.
	 */
	static int medianOfThree(int a[], int f, int l) {
		int m = (f + l) / 2;
		if (a[f] < a[m]) {
			if (a[f] >= a[l])
				return a[f];
			else if (a[m] < a[l])
				return a[m];
		} else {
			if (a[f] < a[l])
				return a[f];
			else if (a[m] >= a[l])
				return a[m];
		}
		return a[l];
	}
	
	/*
	 * Find the median of three of a list, the three numbers being the ones at first, middle, and last indices of the list.
	 */
	static int medianOfThree(List<Integer> list, int f, int l) {
		int m = (f + l) / 2;
		if (list.get(f) < list.get(m)) {
			if (list.get(f) >= list.get(l))
				return list.get(f);
			else if (list.get(m) < list.get(l))
				return list.get(m);
		} else {
			if (list.get(f) < list.get(l))
				return list.get(f);
			else if (list.get(m) >= list.get(l))
				return list.get(m);
		}
		return list.get(l);
	}

	static void quickSort(List<Integer> l) {
		int pivot = medianOfThree(l, 0, l.size());
		int temp = l.get(0);
		l.set(0, pivot);
		l.set(l.indexOf(pivot), temp);
		// ////////////
	}

	static void heapSort(List<Integer> l) {
		int size = l.size();
		int current = l.size();
		int temp, i, left, right, bigger = 0;
		l.add(0, Integer.MIN_VALUE);
		// heapify
		int swapIndex = 0;
		while (current > 1) {
			right = l.get(current);
			left = l.get(current - 1);
			bigger = 0;
			if (right >= left) {
				bigger = right;
				swapIndex = current;
			} else {
				bigger = left;
				swapIndex = current - 1;
			}
			if (bigger > l.get(current / 2)) {
				temp = l.get(current / 2);
				l.set(current / 2, l.get(swapIndex));
				l.set(swapIndex, temp);

				// go back down the tree
				i = swapIndex * 2;
				while (i < l.size()) {
					left = l.get(i);
					right = l.get(i + 1);
					if (right >= left) {
						bigger = right;
						swapIndex = i + 1;
					} else {
						bigger = left;
						swapIndex = i;
					}
					if (bigger > l.get(swapIndex / 2)) {
						temp = l.get(swapIndex / 2);
						l.set(swapIndex / 2, l.get(swapIndex));
						l.set(swapIndex, temp);
					}
					i = swapIndex * 2;
				}
			}
			current -= 2;
		}
		System.out.println(l);

		// position
		int maxIndex = l.size() - 1;
		while (maxIndex > 1) {
			temp = l.get(1);
			l.set(1, l.get(maxIndex));
			l.set(maxIndex, temp);

			// adjust
			current = 1;
			i = current * 2;
			System.out.println("maxIndex" + maxIndex);
			while (i < maxIndex - 1) {
				System.out.println(i);
				left = l.get(i);
				if (i + 1 < maxIndex - 1) {
					right = l.get(i + 1);
					if (right >= left) {
						bigger = right;
						swapIndex = i + 1;
					} else {
						bigger = left;
						swapIndex = i;
					}
				}
				System.out.println("bigger" + bigger);
				if (bigger > l.get(swapIndex / 2)) {
					temp = l.get(swapIndex / 2);
					l.set(swapIndex / 2, l.get(swapIndex));
					l.set(swapIndex, temp);
				}
				i = swapIndex * 2;
			}
			System.out.println("out");
			maxIndex--;
		}
		l.remove(0);
		System.out.println(l);
	}

	static void shellSort(List<Integer> l) {
		int count = 1;
		int c = 0;
		int k = l.size() / 2;
		int forgotten = -1;
		boolean boo = false;
		if (l.size() % 2 == 1) {
			forgotten = l.get(l.size() - 1);
			boo = true;
		}
		int numRepeats = (int) (Math.log(l.size()) / Math.log(2));
		while (count < numRepeats) {
			System.out.println(k);
			Integer[][] colArrays = new Integer[k][l.size() / k];
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < colArrays[i].length; j++) {
					colArrays[i][j] = l.get(i + c);
					c += k;
				}
				c = 0;
			}
			l = new ArrayList<Integer>();
			for (Integer[] a : colArrays) {
				printArray(a);
				insertionSort(Arrays.asList(a));
				for (int b : a) {
					l.add(b);
				}
			}
			k = (int) Math.ceil(k / 2);
			count++;
			System.out.println(l);
		}
		if (boo) {
			l.add(forgotten);
		}
		;
		insertionSort(l);
		System.out.println(l);
	}

	/*
	 * Sort list using bubble sort.
	 */
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
	}

	/*
	 * Sort list using insertion sort.
	 */
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
	}

	/*
	 * Sort list using selection sort.
	 */
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
	}

	/*
	 * Sort list using bucket sort.
	 */
	static void bucketSort(List<Integer> l) {
		int max = Collections.max(l);
		int[] arr = new int[max + 1];
		int[] counters = new int[max + 1];
		for (int i = 0; i < l.size(); i++) {
			arr[l.get(i)] = l.get(i);
			counters[l.get(i)]++;
		}
		List<Integer> newList = new ArrayList<Integer>();
		for (int i : arr) {
			while (counters[i] > 0) {
				newList.add(arr[i]);
				counters[i]--;
			}
		}
		l = newList;
	}

	/*
	 * Look for an element in a list using binary search.
	 */
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

	/*
	 * Look for an element in a list using linear search.
	 */
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

	/*
	 * Recursive method to solve the Towers of Hanoi problem.
	 */
	static void towersOfHanoi(int n, int x, int y, int z) {
		if (n > 0) {
			towersOfHanoi(n - 1, x, z, y);
			System.out.println("Move ring " + n + " from " + x + " to " + y
					+ ".");
			towersOfHanoi(n - 1, z, y, x);
		}
	}

	/*
	 * Recursive method to find the fibonacci of the nth number.
	 */
	static int fibonacci(int n) {
		int result = 0;

		if (n == 0 || n == 1)
			result = 1;
		else
			result = fibonacci(n - 1) + fibonacci(n - 2);

		return result;
	}

	/*
	 * Recursive method to find the factorial of a number.
	 */
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

	/*
	 * This method will sum powers of 2 up to the nth power
	 * Example: n = 2 => 2^0 + 2^1 + 2^2
	 */
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

	static void print2DArray(int[][] a) {
		for (int[] i : a) {
			for (int j : i)
				System.out.print(j + " ");
			System.out.println();
		}
		System.out.println();
	}

	static void printArray(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	static void printArray(Integer[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
