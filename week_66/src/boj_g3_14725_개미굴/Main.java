package boj_g3_14725_개미굴;

import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        Map<String, TrieNode> antHomeInfo = new TreeMap<>(); // 사전 순 정렬
    }

    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 정보 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());
            TrieNode curr = root;

            for (int j = 0; j < count; j++) {
                String food = st.nextToken();
                curr.antHomeInfo.putIfAbsent(food, new TrieNode());
                curr = curr.antHomeInfo.get(food);
            }
        }

        br.close();

        print(root, 0, bw);
        bw.flush();
        bw.close();
    }

    static void print(TrieNode node, int depth, BufferedWriter bw) throws IOException {
        for (String key : node.antHomeInfo.keySet()) {
            for (int i = 0; i < depth; i++) {
                bw.write("--");
            }
            bw.write(key + "\n");
            print(node.antHomeInfo.get(key), depth + 1, bw);
        }
    }

}
