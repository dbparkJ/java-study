package array;

public class Array1Ref1 {
    public static void main(String[] args) {
        int[] students;
        students = new int[5];

        //변수 값 대입

        students[0] = 90;
        students[1] = 80;
        students[2] = 70;
        students[3] = 60;
        students[4] = 50;


        System.out.println("학생1 점수:" + students[0]);
        System.out.println("학생2 점수:" + students[1]);
        System.out.println("학생3 점수:" + students[2]);
        System.out.println("학생4 점수:" + students[3]);
        System.out.println("학생5 점수:" + students[4]);

        String[] strs;
        strs = new String[5];

        strs[0] = "hi";
        strs[1] = "my";
        strs[2] = "name";
        strs[3] = "is";
        strs[4] = "John";

        System.out.println(strs[0]+" "+strs[1]+" "+strs[2]+" "+strs[3]+" "+strs[4]);
    }
}
