package boj_s4_11656_접미사배열;

import java.util.Arrays;
import java.util.Scanner;

// Arrays.sort 메서드 이용 
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 문자열 입력 받기
		String str = sc.next();

		// 문자열 길이 변수에 담아두기
		int n = str.length();

		// 문자열 배열 넣을 수 있게 생성
		String[] strArr = new String[n];
		// 문자열 길이만큼 회전하면서
		for (int i = 0; i < n; i++) {
			strArr[i] = str.substring(i, n);
		}

		sc.close();

		Arrays.sort(strArr); // 그냥 method 활용 한 것

		// 출력 확인
		for (int i = 0; i < n; i++) {
			System.out.println(strArr[i]);
		}

	}

}
