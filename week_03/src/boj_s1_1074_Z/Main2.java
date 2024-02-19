package boj_s1_1074_Z;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {

	// 변수 선언
	static int count = 0;
	static int resultR, resultC;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/boj_s1_1074_Z/input.txt"));

		// 입력 값 받기
		int N = sc.nextInt();
		resultR = sc.nextInt();
		resultC = sc.nextInt();

		// 재귀 함수 호출
		makeZ(N, 0, 0, 0); // (r,c) 좌표는 (0,0)부터 시작. count도 0부터 시작!

		sc.close();
	}

	// 재귀 함수
	public static void makeZ(int N, int r, int c, int count) {

		// 1. 재귀함수의 기저 조건(멈추는 조건)
		if (N == 0) {
			if (r == resultR && c == resultC) {
				System.out.println(count);
			}
			return;
		}

		// 2. 재귀 부분(반복)

		// 변수
		int size = (int) Math.pow(2, N - 1); // 재귀적으로 절반씩 자릅니다.
		int skip = size * size; // size 크기 만큼 count++ 될 것이기 때문에, 한 텀에 size*size 만큼 skip합니다.

		// 첫 번째 경우: 2사분면에 위치할 때
		if (resultR < r + size && resultC < c + size) {
			makeZ(N - 1, r, c, count);
		}

		// 두 번째 경우: 1사분면에 위치할 때
		else if (resultR < r + size && resultC >= c + size) {
			makeZ(N - 1, r, c + size, count + skip);
		}

		// 세번째 경우: 3사분면에 위치 할 때
		else if (resultR >= r + size && resultC < c + size) {
			makeZ(N - 1, r + size, c, count + skip * 2);
		}

		// 네 번째 경우: 4사분면에 위치 할 때
		else {
			makeZ(N - 1, r + size, c + size, count + skip * 3);
		}

	}

}
