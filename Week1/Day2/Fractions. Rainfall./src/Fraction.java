// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Fraction {
    private long numerator;
    private long denominator;

    //constructor
    public Fraction() {
        this.numerator = 0;
        this.denominator = 1;

    }

    public Fraction(long n, long d) {
        try {
            if (d == 0) {
                throw new IllegalArgumentException("Denominator cannot be zero.");
            }
            long gcd = GCD(n, d);
            numerator = n / gcd;
            denominator = d / gcd;
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
        } catch (IllegalArgumentException e) {
            // 处理IllegalArgumentException异常
            System.out.println("IllegalArgumentException: " + e.getMessage());
            // 可以选择抛出其他异常或采取其他适当的处理方法
        }
    }

    private long GCD(long n, long d) {
        long gcd = n;
        long remainder = d;
        while( remainder != 0 ) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return gcd;
    }

    // add
    public Fraction plus(Fraction rhs) {
        long newNumerator = this.numerator * rhs.denominator + rhs.numerator * this.denominator;
        long newDenominator = this.denominator * rhs.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // minus
    public Fraction minus(Fraction rhs) {
        long newNumerator = this.numerator * rhs.denominator - rhs.numerator * this.denominator;
        long newDenominator = this.denominator * rhs.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // multiple
    public Fraction times(Fraction rhs) {
        long newNumerator = this.numerator * rhs.numerator;
        long newDenominator = this.denominator * rhs.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // divided
    public Fraction dividedBy(Fraction rhs) {
        try {
            if (rhs.numerator == 0) {
                throw new ArithmeticException("Cannot divide by 0");
            }
            long newNumerator = this.numerator * rhs.denominator;
            long newDenominator = this.denominator * rhs.numerator;
            return new Fraction(newNumerator, newDenominator);
        } catch (ArithmeticException e) {
            // 处理ArithmeticException异常
            System.out.println("ArithmeticException: " + e.getMessage());
            // 可以选择抛出其他异常或采取其他适当的处理方法
            return null; // 返回一个适当的值或null
        }
    }

    // 倒数
    public Fraction reciprocal() {
        if (this.numerator == 0) {
            throw new ArithmeticException("0 cannot be numerator");
        }
        return new Fraction(this.denominator, this.numerator);
    }

    // convert to double
    public double toDouble() {
        return (double) this.numerator / this.denominator;
    }

    // 转换为字符串
    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }


//    private void reduce() {
//        long gcd = GCD(numerator,denominator);
//        this.numerator /= gcd;
//        this.denominator /= gcd;
//    }
//    public static void main(String[] args) {
//        Fraction fraction1 = new Fraction(3, 4);
//        Fraction fraction2 = new Fraction(1, 2);
//
//        Fraction sum = fraction1.plus(fraction2);
//        Fraction difference = fraction1.minus(fraction2);
//        Fraction product = fraction1.times(fraction2);
//        Fraction quotient = fraction1.dividedBy(fraction2);
//        Fraction reciprocal = fraction1.reciprocal();
//
//        System.out.println("Fraction 1: " + fraction1);
//        System.out.println("Fraction 2: " + fraction2);
//        System.out.println("Sum: " + sum);
//        System.out.println("Difference: " + difference);
//        System.out.println("Product: " + product);
//        System.out.println("Quotient: " + quotient);
//        System.out.println("Reciprocal of Fraction 1: " + reciprocal);
//    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }
}
