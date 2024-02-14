// 기창 님 코드 

// - 원형 Queue 사용하지 않고, Queue 사이즈를 2배로 만들어서 구현 
// - queue의 size 구현을 새로운 방법으로 만들었네! rear - front 

package boj_s4_2164_카드2;

import java.util.Scanner;

public class Main3 {
	
	public static int[] queue;
	public static int front;
	public static int rear;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 가장 큰 카드 번호 N 입력
		int N = sc.nextInt();
		
		// 큐 생성
		queue = new int[2*N]; // queue 사이즈를 2배로 
		front = -1;
		rear = -1;
		
		// 큐에 1부터 N까지 카드 입력
		for (int i = 1; i <= N; i++) {
			enQueue(i);
		}
		
		// 카드가 한장 남을때까지
		// 맨 앞에서 한장 제거 + 다음 한 장을 뽑아서 맨 뒤에 입력을 반복
		while (size() != 1) {
			deQueue();
			enQueue(deQueue());
		}
		
		// 결과 (마지막에 남은 카드) 출력
		System.out.println(deQueue());
		
	}
	
	// 큐 구현
	public static void enQueue(int num) {
		queue[++rear] = num;
	}
	
	public static int deQueue() {
		return queue[++front];
	}
	
	public static int size() {
		return rear - front;
	}
}
