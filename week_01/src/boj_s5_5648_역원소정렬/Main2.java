// 기창 님 코드 - 방법 1. 한글자씩 숫자로 변환 후 사칙연산 이용

package boj_s5_5648_역원소정렬;

import java.util.Scanner;

public class Main2 {
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
	// 뒤에서부터 한 글자씩 정수로 변환 후
	// 10을 곱하고 다음 숫자를 더하는 과정 반복
	public static long reverse(String str) {
		long result = 0;

		int length = str.length();
		int[] arr = new int[length];

		for (int i = 0; i < length; i++) {
			arr[i] = (int) (str.charAt(length - 1 - i) - '0');
		}

		for (int i = 0; i < length; i++) {
			result = result * 10 + arr[i];
		}

		return result;
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