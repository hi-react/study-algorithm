package boj_g3_14725_개미굴;

import java.io.*;
import java.util.*;

public class Main2 {
    static class TrieNode {
        Map<String, TrieNode> antHomeInfo = new TreeMap<>(); // 사전 순 정렬

        void insert(String key) {
            antHomeInfo.putIfAbsent(key, new TrieNode());
        }

        TrieNode get(String key) {
            return antHomeInfo.get(key);
        }
    }

    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 정보 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());
            TrieNode curr = root;

            for (int j = 0; j < count; j++) {
                String food = st.nextToken();
                curr.insert(food);
                curr = curr.get(food);
            }
        }

        br.close();

        print(root, 0);
    }

    static void print(TrieNode node, int depth) {
        for (String key : node.antHomeInfo.keySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(key);
            print(node.antHomeInfo.get(key), depth + 1);
        }
    }

}
