package cond;

public class CondOp1 {
    public static void main(String[] args) {
        int age = 10;
        String status;

        if (age >= 18) {
            status = "성인";
        } else {
            status="미성년자";
        }
        System.out.println(status);

        status = (age >= 18) ? "성인" : "미성년자";
        System.out.println(status);
    }
}
