package boj_g5_17352_여러분의다리가되어드리겠습니다;

import java.io.*;
import java.util.*;

/**
 * 유니온 파인드(Union-Find)
 *
 * 서로소 집합(Disjoint Set)을 관리하는 알고리즘
 * 주로 그래프에서 연결 여부를 효율적으로 판단할 때 사용
 * */

public class Main2 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 유니온 파인드 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i; // 초기: 자기 자신이 부모
        }

        // 다리 정보 입력 및 유니온 연산
        for (int i = 0; i < N - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v); // 두 섬 연결
        }

        // 분리된 두 섬 찾기
        int first = -1, second = -1;

        for(int i = 1; i <= N; i++) {
            int root = find(i); // 현재 섬의 부모 찾기
            if (first == -1) {
                first = root; // 첫 번째 분리 섬
            } else if (first != root) {
                second = root; // 두 번째 분리 섬
                break;
            }
        }

        br.close();
        bw.write(first + " " + second);
        bw.flush();
        bw.close();
    }

    /**
     * [method 1] 두 집합 합치는 연산
     * */
    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 병합
        }
    }

    /**
     * [method 2] 최상위 노드(부모) 찾기
     * 경로 압축 (Path Compression) 통해 트리 깊이 최소화
     * @return 최상위 노드(부모)
     * */
    private static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }
}
