// 장우 님 코드

// - Java 내장되어 있는 Queue Interface 이용
// - 구현체는 LinkedList
// - method: poll, offer, peek 사용 

package boj_s4_2164_카드2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();

		Queue<Integer> cardDump = new LinkedList<>();

		for (int i = 1; i <= cnt; i++) {
			cardDump.offer(i);
		}

		while (cardDump.size() > 1) {
			cardDump.poll();
			int pollCard = cardDump.poll();
			cardDump.offer(pollCard);
		}

		System.out.println(cardDump.peek());
	}
}