package boj_s4_2164_카드2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// N장의 카드 값 받아서
		int n = sc.nextInt();

		// Queue 객체 생성 (n 크기의 queue 배열 생성)
		Queue queue = new Queue(n);

		// queue에 카드 채워 넣기
		for (int i = 1; i <= n; i++) {
			queue.enQueue(i);
		}

		// 카드 1장 남을 때 까지 반복 할 건데,
		while (queue.size > 1) {

			// 일단 제일 앞의 값 버린다.
			queue.deQueue();

			// 그 다음 값을 버리고, 해당 값을 queue 가장 앞으로 넣어준다.
			int value = queue.deQueue();
			queue.enQueue(value);

		}

		queue.printQueue();

	}

	// 원형 Queue 구현 - 배열
	static class Queue {

		// 멤버 변수
		private int[] queue; // int 배열로 이루어짐
		private int front, rear, size;

		// 기본 생성자
		Queue() {
		}

		// 매개 변수 있는 생성자
		Queue(int n) {
			this.queue = new int[n];
			this.front = 0;
			this.rear = 0;
			this.size = 0; // 초기 사이즈 0
		}

		// method

		// 1. 포화 상태 확인
		boolean isFull() {
			return size == queue.length;
		}

		// 2. 공백 상태 확인
		boolean isEmpty() {
			return size == 0;
		}

		// 3. 삽입
		void enQueue(int data) {
			if (isFull()) {
				System.out.println("queue가 꽉 찼습니다.");
				return;
			}
			queue[rear] = data; // rear 자리에 data 넣어주고
			rear = (rear + 1) % queue.length; // rear + 1 하는데, 원형 큐니까 나머지 연산자 사용
			size++;
		}

		// 4. 삭제
		int deQueue() {
			if (isEmpty()) {
				System.out.println("삭제할 데이터가 없습니다.");
				return -1;
			}
			int data = queue[front]; // 가장 앞에 있는 값 임시 저장
			front = (front + 1) % queue.length; // front + 1 하는데, 원형 큐니까 나머지 연산자 사용
			size--;
			return data;
		}

		// 출력
		void printQueue() {
			for (int i = 0; i < size; i++) {
				System.out.print(queue[(front + i) % queue.length] + " ");
			}
			System.out.println();
		}

	}

}
