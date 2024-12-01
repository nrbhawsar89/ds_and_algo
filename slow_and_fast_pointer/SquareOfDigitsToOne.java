
public class SquareOfDigitsToOne {
    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println(checkDigitToOne(19));
    }

    public static boolean checkDigitToOne( int num) {
        int fast;
        int slow;
        while (num != 1) {
            slow = getSumOfDigits(num);
            fast = getSumOfDigits(getSumOfDigits(num));
            if (fast == slow && fast != 1) return false;
            num = slow;
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