package boj_g5_2668_숫자고르기;

/**
 *
 * 1. 문제 분석
 * 첫째 줄에서 숫자를 적절히 뽑아 그 숫자들의 집합을 만든다.
 * 이 때 뽑힌 숫자들의 바로 밑 둘째 줄에 들어 있는 숫자들의 집합과 같아야 한다.
 * 출력: 최대 집합 크기, 집합에 들어가는 숫자들 오름차순 정렬
 *
 * 2. 알고리즘 : 그래프, 사이클 찾기
 * 시작 정점에서 출발하여 다시 그 정점으로 돌아오는 경로 찾기
 *
 */


import java.util.*;

public class Main {

    static int N;
    static int[] secondRow;
    static boolean[] visited;
    static ArrayList<Integer> maxSet;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 총 N 칸
        secondRow = new int[N + 1]; // 숫자 1부터 시작

        for (int i = 1; i <= N; i++) {
            secondRow[i] = sc.nextInt(); // 두 번째 줄 입력 받기
        }

        sc.close();

        maxSet = new ArrayList<>(); // 반환할 최대 set 결과
        visited = new boolean[N + 1]; // 방문 처리

        // 하나씩 돌면서 사이클 발생하는 지 확인
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false); // 방문 배열 초기화
            visited[i] = true;
            dfs(i, i);
        }

        Collections.sort(maxSet); // 오름차순 정렬
        System.out.println(maxSet.size());
        for (int i = 0; i < maxSet.size(); i++) {
            System.out.println(maxSet.get(i));
        }
    }

    /**
     * N = 7
     * secondRow = [0, 3, 1, 1, 5, 5, 4, 6]
     *
     * 정점 1에서 시작해 visited[1] = true
     * dfs(1, 1) 호출
     * secondRow[1] = 3 방문 안함 -> visited[3] = true
     * dfs(3, 1) 호출
     * secondRow[3] = 1 이 target = 1 과 같으므로 -> maxSet에 1 추가
     *
     * 정점 5에서 시작해 visited[5] = true
     * dfs(5, 5) 호출
     * secondRow[5] = 5 방문 안함 -> visited[5] = true
     * dfs(5, 5) 호출
     * secondRow[5] = 5 가 target = 5 와 같으므로 -> maxSet에 5 추가
     *
     * */
    static void dfs(int start, int target) {

        // 사이클 발생 시, target 넣어주기
        if (secondRow[start] == target) {
            maxSet.add(target);
            return;
        }

        // 현재 정점에 연결된 다음 정점을 방문하지 않았다면 dfs 반복
        if (!visited[secondRow[start]]) {
            visited[secondRow[start]] = true;
            dfs(secondRow[start], target);
            visited[secondRow[start]] = false;
        }
    }
}