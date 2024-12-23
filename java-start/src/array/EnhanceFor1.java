package array;

public class EnhanceFor1 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            System.out.println(number);
        }

        //향상 for문, for-each문, iter로 인텔리제이에서 사용 가능
        for (int number : numbers) {
            System.out.println(number);
        }

        //i 가 필요한 경우에는 일반 for 문을 사용해야 한다.
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("number" + i + "번의 결과는 :" + numbers[i]);
        }
    }
}
