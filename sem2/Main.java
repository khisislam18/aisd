import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 500;
        List<Integer> arr= new ArrayList<>();
        int tmp = n;
        while(tmp > 1){
            for (int i = 2; i*i <= tmp; i++) {
                while(tmp % i == 0){
                    tmp /= i;
                    arr.add(i);
                }
            }
        }
        System.out.println(arr);
        int k = 5;
        int s = 2;
        tmp = s;
        for (int i = 0; i < k - 1; i++) {
            s *= tmp;
        }
    }
}
