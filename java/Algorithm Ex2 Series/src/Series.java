import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Series {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int N;
		while(true) {
			try {
				N = Integer.parseInt(sc.nextLine());	// 수열의 길이를 받는다.
			} catch (Exception e) {	// 더이상 다음 문제가 없으면 종료.
				break;
			}

	
			String[] seriesA = sc.nextLine().split(" ");	// 각 라인의 수열을 읽어 공백으로 나누어 배열에 담는다.
			Arrays.sort(seriesA);	// 문자열 기준으로 정렬되서 1, 10, 2, 3, 4... 이런 순서로 정렬되지만 비교만 할거라 상관 없다.
			String[] seriesB = sc.nextLine().split(" ");
			Arrays.sort(seriesB);

			for (int i = 0; i < N; i++) {	// 입력된 수열의 길이만큼 반복
				if (seriesA[i].equals(seriesB[i])) {
					if (i == N-1) {	// 마지막까지 같다면 True를 출력.
						System.out.println("True");
					}
					continue;
				} else {	// 한 번이라도 서로 다를 경우 False를 출력 후 정지.
					System.out.println("False");
					break;
				}
				
			}

		}
		
	}

}
