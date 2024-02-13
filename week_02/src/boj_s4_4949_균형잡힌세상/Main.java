package boj_s4_4949_균형잡힌세상;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 문자열을 한 줄씩 받아서
		String str = sc.nextLine();

		// 문자열 길이
		int n = str.length();

		// stack class의 객체 생성
		Stack stack = new Stack(n);

		// 괄호 짝이 맞는 지 확인 해 줄 boolean
		boolean balanced = true;

		// 문자열을 하나씩 돌면서
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
				else if (stack.pop() != ']')
					balanced = false;
			}
		}

		if (balanced == true) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}

		sc.close();

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
