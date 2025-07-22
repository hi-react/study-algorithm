package boj_g3_16934_게임닉네임;

import java.io.*;
import java.util.*;

public class Main2 {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
    }

    static TrieNode root = new TrieNode();
    static Map<String, Integer> nameCount = new HashMap<>(); // 동일한 닉네임 개수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 사람 수

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            String alias = makeAlias(name); // 별칭 세팅
            insertAll(name); // 전체 닉네임 Trie 추가
            bw.write(alias + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static void insertAll(String name) {
        TrieNode node = root;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
    }

    static String makeAlias(String name) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        boolean newBranch = false;

        // 1. 닉네임 탐색 => (가장 짧은) 새로운 별칭 찾기
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            sb.append(c);

            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
                newBranch = true; // 새로운 분기 = (가장 짧은) 별칭
                break;
            }
            node = node.children.get(c); // 다음 노드로 세팅
        }

        // 2. 새로운 분기 못 만들었으면 동일 닉네임 => 닉네임 or 닉네임+카운트
        int count = nameCount.getOrDefault(name, 0) + 1;
        nameCount.put(name, count);

        if (newBranch) {
            return sb.toString();
        } else {
            return count == 1 ? name : name + count;
        }
    }
}
