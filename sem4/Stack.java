import java.util.Arrays;

public class Stack {
    private int[] array;
    private int current;
    private int size;

    public Stack(int size) {
        array = new int[size];
        this.size = size;
        current = 0;
    }
    private boolean check(){
        return current > size;
    }
    public boolean add(int s){
        if(check()){
            throw new ArrayIndexOutOfBoundsException();
        }
        array[current++] = s;
        return true;
    }
    public int getFirst(){
        return array[--current];
    }
    public int showFirst(){
        return array[current - 1];
    }
    public boolean isEmpty(){
        return current == 0;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "array=" + Arrays.toString(array) +
                ", current=" + current +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        String str = "(()()())";//00101011
        String str1 = "()()(())";//01010011
        char[] charArray = str.toCharArray();
        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = charArray[i] == '(' ? 0 : charArray[i] == ')' ? 1 : -1;
            if(intArray[i] == -1){
                throw new IllegalArgumentException();
            }
        }
        Stack stack = new Stack(str.length());
        for (int i = 0; i < intArray.length; i++) {
            stack.add(intArray[i]);
        }
        while(true){
            if(stack.isEmpty()){
                System.out.println("Empty");
                break;
            }
        }
    }
}
