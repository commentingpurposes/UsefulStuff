import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Current {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> l = new ArrayList<Integer>();
		l.add(5);
		l.add(12);
		l.add(3);
		l.add(7);
		l.add(4);
		l.add(23);
		l.add(3);
		l.add(-2);
		l.add(24);
		l.add(12);
		l.add(56);
		l.add(34);
		l.add(35);
		l.add(12);
		shellSort(l);
	}

	static void shellSort(List<Integer> l) {
		System.out.println(l);
		int n = l.size();
		int count = 0;
		int c = 0;
		int k = (n % 2 == 0) ? n / 2 : n / 2 + 1;
		int numRepeats = (int) (Math.log(n) / Math.log(2));
		while (count < numRepeats) {
			System.out.println("k:" + k);
			Integer[][] colArrays = new Integer[k][(n % k == 0) ? n / k : n / k
					+ 1];
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < colArrays[i].length; j++) {
					if (i + c < n) {
						colArrays[i][j] = l.get(i + c);
					} else {
						colArrays[i][j] = Integer.MAX_VALUE;
					}
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
			k = (k % 2 == 0) ? k / 2 : k / 2 + 1;
			count++;
			System.out.println("sorted:" + l);
			while (l.contains(Integer.MAX_VALUE))
				l.remove(l.indexOf(Integer.MAX_VALUE));
			System.out.println("sorted:" + l);
		}
		insertionSort(l);
		System.out.println(l);
	}

	static List<Integer> insertionSort(List<Integer> l) {
		for (int i = 1; i < l.size(); i++) {
			int j = i;
			while (j > 0 && l.get(j - 1) > l.get(j)) {
				int temp = l.get(j - 1);
				l.set(j - 1, l.get(j));
				l.set(j, temp);
				j--;
			}
		}

		return l;
	}

	static void printArray(Integer[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
