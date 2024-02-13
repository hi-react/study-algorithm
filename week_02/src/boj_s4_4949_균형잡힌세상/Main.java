package boj_s4_4949_균형잡힌세상;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	// 실행
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("src/boj_s4_4949_균형잡힌세상/input.txt");
		Scanner sc = new Scanner(file);

		while (true) {

			// 문자열을 한 줄씩 받아서 반복할 건데,
			String str = sc.nextLine();

			// 입력 종료 조건
			if (str.equals(".")) {
				sc.close();
				break;
			}

			// 문자열 길이
			int n = str.length() - 1; // 마지막 온점 필요 없으니까 

			System.out.println(isBalanced(str, n) ? "yes" : "no");

		}

	}

	// isBalanced method 구현
	static boolean isBalanced(String str, int n) {

		// stack class의 객체 생성 (크기는 n)
		Stack stack = new Stack(n);

		// 괄호 짝이 맞는 지 확인 해 줄 boolean
		boolean balanced = true;

		// 문자열을 돌면서 문자 하나씩 볼건데,
		for (int i = 0; i < n; i++) {

			// 열린 괄호 라면 스택에 넣어준다.
			if (str.charAt(i) == '(' || str.charAt(i) == '[') {
				stack.push(str.charAt(i));
			}

			// 닫힌 괄호 ')' 라면
			else if (str.charAt(i) == ')') {
				// 스택이 비어있다면, 균형 실패
				if (stack.isEmpty())
					balanced = false;
				else if (stack.pop() != '(')
					balanced = false;

			}

			// 닫힌 괄호 ']' 라면
			else if (str.charAt(i) == ']') {
				// 스택이 비어있다면, 균형 실패
				if (stack.isEmpty())
					balanced = false;
				else if (stack.pop() != '[')
					balanced = false;
			}
		}

		// 스택에 값이 남아 있다면, 짝 안 맞는 것 
		if (!stack.isEmpty())
			balanced = false;

		return balanced;

	}

	// Stack 구현 - 배열
	static class Stack {

		// 멤버 변수
		private char[] stack; // char 배열로 이루어진 stack
		private int top;
		private int size;

		// 기본 생성자
		Stack() {
		}

		// 매개변수로 배열 크기 받아오는 생성자
		Stack(int N) {
			this.stack = new char[N]; // N 크기의 배열 생성
			this.top = -1;
			this.size = N;
		}

		// method

		// 1) isFull
		public boolean isFull() {
			return top == size - 1;
		}

		// 2) isEmpty
		public boolean isEmpty() {
			return top == -1;
		}

		// 3) push
		public void push(char data) {
			if (isFull()) {
				System.out.println("stack이 가득 찼습니다.");
				return;
			}
			stack[++top] = data;
		}

		// 4) pop
		public char pop() {
			if (isEmpty()) {
				System.out.println("stack이 비어 있습니다.");
				return 0;
			}

			return stack[top--];
		}

		// 확인용 출력
		public void printStack() {
			System.out.println(Arrays.toString(stack));
		}

	}

}
