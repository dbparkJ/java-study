package array;

public class ArrayDi4 {
    public static void main(String[] args) {
        // 2*3 2차원 배열을 만든다
        int[][] arr = {
            {1,2,3},
            {4,5,6}
        };

        for (int row = 0; row < arr.length; row++) {
            for (int column = 0; column < arr[row].length; column++) {
                //0행부터 출력
                System.out.print(arr[row][column] + " ");
            }
            System.out.println();
        }

    }
}
