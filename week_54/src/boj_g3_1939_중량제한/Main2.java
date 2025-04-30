package boj_g3_1939_중량제한;

import java.io.*;
import java.util.*;

public class Main2 {

    static class Bridge implements Comparable<Bridge> {
        int from, to, weight;
        Bridge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Bridge o) {
            return o.weight - this.weight; // 다리 중량 기준 내림차순
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬 개수
        int M = Integer.parseInt(st.nextToken()); // 다리 정보

       List<Bridge> bridges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            bridges.add(new Bridge(A, B, C));
        }

        st = new StringTokenizer(br.readLine());
        int factory1 = Integer.parseInt(st.nextToken());
        int factory2 = Integer.parseInt(st.nextToken());

        br.close();

        // 1. 다리 중량 기준 내림차순 정렬
        Collections.sort(bridges);

        // 2. Union-Find 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 3. 다리 중량 큰 것부터 연결하면서 공장끼리 연결되는 순간 찾기
        for (Bridge b : bridges) {
            union(b.from, b.to);

            if(find(factory1) == find(factory2)) {
                System.out.println(b.weight);
                return;
            }
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int start, int end) {
        int rootA = find(start);
        int rootB = find(end);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

}
