package boj_g4_5052_전화번호목록;

import java.io.*;
import java.lang.*;

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine()); // 전화 번호 개수
            Trie trie = new Trie();

            boolean isConsistent = true;

            for (int i = 0; i < N; i++) {
                String phoneNumber = br.readLine();
                if (!trie.insert(phoneNumber)) {
                    isConsistent = false;
                }
            }

            if (isConsistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        br.close();

    }
}

class Trie {
    private final TrieNode root; // final 처리 => 루트 노드가 생성자에서 한 번 초기화 된 이후, 다른 TrieNode 객체로 재할당 되지 않도록 루트 노드 보호

    public Trie() {
        this.root = new TrieNode(); // 루트 노드 초기화
    }

    public boolean insert(String phoneNumber) {
        TrieNode currentNode = root; // 최초 전화 번호 삽입은 루트 부터 시작
        boolean isNewRoute = false; // 삽입된 번호가 새로운 경로를 형성 하는 지 체크

        for (int i = 0 ; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);

            // 현재 숫자에 해당하는 자식 노드가 없으면,
            if (currentNode.children[c - '0'] == null) {
                currentNode.children[c - '0'] = new TrieNode(); // 새로운 TrieNode 생성
                isNewRoute = true; // 새로운 경로 생성했으니까 true 처리
            }

            currentNode = currentNode.children[c - '0'];

            // 1) 기존 번호가 현재 번호의 접두어인 경우 => => 일관성 깨짐(false)
            if (currentNode.isEndOfNumber) {
                return false;
            }
        }

        // 현재 번호 삽입 완료 하면, 전화 번호 끝임을 표시
        currentNode.isEndOfNumber = true;

        return isNewRoute; // 새로운 경로 추가 되지 않았다면, 2) 현재 번호는 기존 번호의 접두어 => 일관성 깨짐(false)
    }
}

class TrieNode {
    TrieNode[] children; // 자식 노드 배열 (0 ~ 9 숫자를 표현)
    boolean isEndOfNumber; // 해당 노드가 전화번호 끝인지 여부

    public TrieNode() {
        this.children = new TrieNode[10]; // 10짜리 배열
        this.isEndOfNumber = false;
    }
}
