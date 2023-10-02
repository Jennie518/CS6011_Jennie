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
        if (d == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        long gcd = GCD(n, d);
        this.numerator = n / gcd;
        this.denominator = d / gcd;
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
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

    // 加法
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
        if (rhs.numerator == 0) {
            throw new ArithmeticException("Cannot divided by 0");
        }
        long newNumerator = this.numerator * rhs.denominator;
        long newDenominator = this.denominator * rhs.numerator;
        return new Fraction(newNumerator, newDenominator);
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


    private void reduce() {
        long gcd = GCD(numerator,denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }
}
