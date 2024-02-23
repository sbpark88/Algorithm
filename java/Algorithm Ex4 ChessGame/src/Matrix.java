import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);

		int problemN = 1;
		int rabbitNumber;
		int result = 0;
		
		while(true) {
			try {
				rabbitNumber = sc.nextInt();	// 토끼의 수를 입력 받는다.
			} catch (Exception e) {
				break;
			}
			
			// 여우의 좌표를 입력 받는다.
			int fox_x = sc.nextInt();
			int fox_y = sc.nextInt();
			
			// 그냥 1차원 배열만 쓰는게 더 빠를 것 같은데 연습하느라 2차원 배열 사용해봄... 토끼의 좌표 입력 받기.
			int rabbitCoordinate[][] = new int[rabbitNumber][2];
			
			for (int i = 0; i < rabbitNumber; i++) {
				rabbitCoordinate[i][0] = sc.nextInt();	// i 번째 토끼의 x좌표
				rabbitCoordinate[i][1] = sc.nextInt();	// i 번째 토끼의 y좌표

				// 토끼가 여우와 같은 x축이나 y축에 있다면 true
				if ((rabbitCoordinate[i][0] == fox_x) | (rabbitCoordinate[i][1] == fox_y)) {
					result++;
					continue;
				}
				
				// 토끼가 여우와 기울기 +1 직선 또는 -1 직선에 있다면 true (나눗셈의 경우 0으로 나누는 경우에 대한 예외처리를 해야하지만,
				// 위 if문에서 분모가 0인 경우(y = fox_y 직선 위에 있는 경우) continue를 통해 아래 나눗셈 if문으로 들어오지 않기 때문에 중복 검증을 하지 않음)
				if ( ( ( (fox_y - rabbitCoordinate[i][1]) / (fox_x - rabbitCoordinate[i][0]) ) == 1) | 
					 ( ( (fox_y - rabbitCoordinate[i][1]) / (fox_x - rabbitCoordinate[i][0]) ) == -1)) {
					result++;
				}
			}
			
			System.out.printf("%d번 문제의 답은 : %d \n", problemN, result);
			problemN++;
			result = 0;

		}
	}
}