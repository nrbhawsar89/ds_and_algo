
public class SquareOfDigitsToOne {
    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println(checkDigitToOne(19));
    }

    public static boolean checkDigitToOne( int num) {
        int fast = num;
        int slow = num;
        while (fast != 1 && slow != 1) {
            slow = getSumOfDigits(slow);
            fast = getSumOfDigits(getSumOfDigits(fast));
            if (fast == slow && fast != 1) return false;

        }
        return true;
    }

    private static int getSumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += Math.pow(num%10, 2);
            num = num/10;
        }
        return sum;
    }
}