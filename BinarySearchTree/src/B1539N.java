import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeMap;

public class B1539N {

	
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input_num = Integer.parseInt(br.readLine());
        TreeMap<Long, Long> map = new TreeMap<>();
        long count = 1;

        // 처음 입력값은 root임
        long root = Long.parseLong(br.readLine());
        map.put(root, (long) 1);
        //System.out.println(0);

        while (--input_num > 0) {
            long input_value = Long.parseLong(br.readLine());
            long height;

          
                height = Long.max(
                        (map.ceilingKey(input_value) != null)?
                                map.get(map.ceilingKey(input_value)) : 0,
                        (map.floorKey(input_value) != null)?
                                map.get(map.floorKey(input_value)) : 0
                );
           

            if (height == 0) { // 비교할 값이 없는 높이가 1인 경우
                map.put(input_value, (long) 2);
                count+=2;
            } else { // 높이 2 이상인 것
                map.put(input_value, height + 1);
                count += height + 1;
            }

           // System.out.println(count);
        }
        System.out.println(count);
    }
}
