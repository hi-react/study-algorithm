// 기창 님 코드 - 방법2. 뒤에서부터 한글자씩 문자열에 추가 후 Long형으로 변경

package boj_s5_5648_역원소정렬;

import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// 숫자 범위가 10^12까지이므로 long 사용
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = reverse(sc.next());
		}

		// 정렬 후 결과 출력
		selectionSort(arr);
		for (int i = 0; i < N; i++) {
			System.out.println(arr[i]);
		}
	}

	// 숫자를 뒤집는 메소드
	// StringBuilder 사용
	// 뒤에서부터 한글자씩 받아서 문자열로 만들고
	// Long.parseLong으로 변환
	public static long reverse(String str) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(str.length() - 1 - i));
		}

		String reversedStr = sb.toString();

		return Long.parseLong(reversedStr);
	}

	// 선택정렬
	public static void selectionSort(long[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minIdx] > arr[j]) {
					minIdx = j;
				}
			}
			long tmp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = tmp;
		}
	}

}