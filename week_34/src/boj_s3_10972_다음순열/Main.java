package boj_s3_10972_다음순열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

//        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        // 1) 입력 받기
        int n = Integer.parseInt(br.readLine()); // 순열 크기
        int[] sequence = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // 2) 다음 순열 찾아서
        if (nextPermutation(sequence)) {
            // 3) 결과 출력
            for (int num : sequence) {
                System.out.print(num + " ");
            }
        } else {
            // 마지막 순열 이라면
            System.out.println(-1);
        }
    }

    // [method] 다음 순열 찾는 메서드
    private static boolean nextPermutation(int[] sequence) {
        int n = sequence.length;
        int i = n - 2;

        // 1) 뒤에서부터 첫 번째로 감소하는 위치 찾기
        // = 순열을 더 크게 만들기 위해 변경할 필요가 있는 위치
        // why? 이미 내림차순 정렬되어 있다면(5 4 3 2 1), 더 큰 순열로 만들 수 없다.
        // ex) 1, 2, 3, 6, 5, 4 => 여기서 결국 i = 2
        while (i >= 0 && sequence[i] >= sequence[i + 1]) {
            i--;
        }

        if (i == -1) {
            return false; // 마지막 순열인 경우 ex) 5 4 3 2 1
        }

        // 2) sequence[i]보다 큰 값을 뒤에서 부터 찾아 교환
        // sequence[i]보다 크면서 가능한 한 작은 값을 찾아야 함
        // 이 값을 교환해야 현재 순열보다 큰 순열을 만들면서도 가능한 한 작은 순열 만들 수 있으므로
        // i 이후로는 내림차순 정렬되어 있기 때문에 가장 오른쪽 값이 가장 작은 값
        // ex) sequence[i] = 3, sequence[j] = 4 교환 => 최종 1, 2, 4, 6, 5, 3
        for (int j = n - 1; j > i; j--) {
            if (sequence[i] < sequence[j]) {
                // 교환
                int tmp = sequence[i];
                sequence[i] = sequence[j];
                sequence[j] = tmp;
                break;
            }
        }

        // 3) i 이후 부분을 오름차순 정렬
        // 가능한 작은 순열 만들기 위해
        Arrays.sort(sequence, i + 1, n);
        return true;
    }
}
