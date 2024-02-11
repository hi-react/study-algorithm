// 주형님 코드 

package boj_s5_5648_역원소정렬;

import java.util.Scanner;

public class Main6 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		String[] elements = new String[N];
		long[] list = new long[N];

		for (int i = 0; i < N; i++) {
			String s = sc.next();
			char[] sch = s.toCharArray();
			char[] rv = new char[sch.length];

			// 문자 뒤집기
			for (int j = 0; j < sch.length; j++) {
				rv[j] = sch[sch.length - 1 - j];
			}

			String reverse_s = "";
			for (char ch : rv) {
				reverse_s += ch;
			}

			elements[i] = reverse_s;
		}

		for (int i = 0; i < list.length; i++) {
			list[i] = Long.valueOf(elements[i]);
		}

		selectionSort(list);

		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}

//		이거도 통과함
//		for(int i = 0; i < N; i++) {
//			StringBuffer sb = new StringBuffer(elements[i]);
//			String reverseStr = sb.reverse().toString();
//			list[i] = Long.valueOf(reverseStr);
//		}
//		
//		Arrays.sort(list);
//		for(int i = 0; i < N; i++) {
//			System.out.println(list[i]);
//		}

	}

	public static void selectionSort(long[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			long tmp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = tmp;
		}
	}
}