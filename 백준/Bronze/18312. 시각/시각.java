import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = 0;

		for (int h1 = 0; h1 <= N; h1++) { // 3600
			if (h1 % 10 == K || h1/10 == K) { // K?시나 ?K시일 경우
				cnt += 3600;
				continue;
			}
			for (int m10 = 0; m10 <= 5; m10++) { // 600
				if (m10 == K) {
					cnt += 600;
					continue;
				}
				for (int m1 = 0; m1 <= 9; m1++) { // 60
					if (m1 == K) {
						cnt += 60;
						continue;
					}
					for (int s10 = 0; s10 <= 5; s10++) { // 10
						if (s10 == K) {
							cnt += 10;
							continue;
						}
						for (int s1 = 0; s1 <= 9; s1++) { // 1
							if (s1 == K) {
								cnt += 1;
								continue;
							}
						}
					}
				}
			}
		}

		System.out.println(cnt);

	}

}