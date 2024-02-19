package boj_s1_1074_Z;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	// 변수 선언
	static int[][] arr;
	static int count = 0;
	static int r = 0, c = 0;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/boj_s1_1074_Z/input.txt"));

		// 입력 값 받기
		int N = sc.nextInt();
		int resultR = sc.nextInt();
		int resultC = sc.nextInt();

		// 2^n * 2^n 배열 생성
		int squareN = (int) Math.pow(2, N); // N은 최대 15이므로, int 타입 범위로 casting 가능
		arr = new int[squareN][squareN];

		// 재귀 함수 호출
		makeZ(N, r, c); // (0,0)부터 시작할 거니까, r과 c=0부터 넣어준다.

		// 결과 값 출력
		System.out.println(arr[resultR][resultC]);

		sc.close();
	}

	// 재귀 함수
	public static void makeZ(int N, int r, int c) {

		// 재귀함수의 기저 조건(멈추는 조건)
		if (N == 0) {
			arr[r][c] = count++;
			return;
		}

		// 재귀 부분(반복)
		int size = (int) Math.pow(2, N - 1);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				makeZ(N - 1, r + i * size, c + j * size);
			}
		}

	}

}
