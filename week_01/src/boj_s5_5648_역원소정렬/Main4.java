// 승호님 코드 

package boj_s5_5648_역원소정렬;

import java.util.Arrays;
import java.util.Scanner;

public class Main4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		long[] ans = new long[T];

		for (int i = 0; i < T; i++) {
			char[] a = sc.next().toCharArray();
			char[] b = new char[a.length];
			for (int j = 0; j < a.length; j++) {
				b[j] = a[a.length - j - 1];
			}
			// 문자를 뒤집고 정수로 바꾸는데 만약 정수 앞에 00001 이런식이면 정수화 할때 0이 없어짐
			String c = new String(b);
			ans[i] = Long.parseLong(c);
		}

		Arrays.sort(ans);
		for (int i = 0; i < T; i++) {
			System.out.println(ans[i]);
		}

	}
}