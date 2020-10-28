import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// https://www.daleseo.com/sort-quick/
public class QuickSort1 {

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(1);	array.add(5);	array.add(3);	array.add(4);	array.add(2);

        System.out.println(quickSort(array));

        System.out.println(Arrays.toString(array.toArray()));

    }

    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() <= 1) return list;
        int pivot = list.get(list.size() / 2);

        List<Integer> lesserArr = new LinkedList<>();
        List<Integer> equalArr = new LinkedList<>();
        List<Integer> greaterArr = new LinkedList<>();

        for (int num : list) {
            if (num < pivot) lesserArr.add(num);
            else if (num > pivot) greaterArr.add(num);
            else equalArr.add(num);
        }

        return Stream.of(quickSort(lesserArr), equalArr, quickSort(greaterArr))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
    // https://futurecreator.github.io/2018/08/26/java-8-streams/
    // https://napasun-programming.tistory.com/39
}
