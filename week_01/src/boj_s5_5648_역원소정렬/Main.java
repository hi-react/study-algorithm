package boj_s5_5648_역원소정렬;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("src/boj_s5_5648_역원소정렬/input.txt");
		Scanner sc = new Scanner(file);

		int N = sc.nextInt(); // N개의 양의 정수

		// N짜리 배열 만들어서 값 넣어주기
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.next();
		}

		sc.close(); // 입력받는 건 끝남

		// 위의 String 배열 value 모두 뒤집어서 long 타입으로 변환 (0 알아서 사라짐)
		long[] reversedArr = reverse(N, arr);

		// 다시 오름차순 정렬
		long[] sortedArr = sorted(reversedArr);

		// 출력
		for (int i = 0; i < sortedArr.length; i++) {
			System.out.println(sortedArr[i]);
		}

	}

	// String 배열 값들을 reverse 해주고, String -> Long 형 변환 해주는 method
	private static long[] reverse(int N, String[] arr) { // String으로 이루어진 arr 배열을 받아서

		// 뒤집은 애들을 넣어줄 새로운 num 배열 생성해두자.
		long[] reversedArr = new long[N]; // 입력하는 정수는 10^12을 넘어선 안 됩니다.

		for (int i = 0; i < N; i++) {
			char[] str = arr[i].toCharArray(); // 하나씩 문자열 가져와서 ex.[2, 2, 3, 3]
			String reversedStr = "";
			for (int j = str.length - 1; j >= 0; j--) {
				reversedStr += str[j];
			}
			// String -> long 형 변환
			reversedArr[i] = Long.parseLong(reversedStr);
		}

		return reversedArr;

	}

	// 오름차순 정렬 method - 선택 정렬
	private static long[] sorted(long[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIdx])
					minIdx = j;
			}
			long tmp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = tmp;
		}

		return arr;
	}

}
