// This class represents a Fraction.
public class Fraction implements Comparable<Fraction> {

    // Numerator (top part) of the fraction.
    private long numerator;
    // Denominator (bottom part) of the fraction.
    private long denominator;

    // Constructor that makes a default fraction of 0/1.
    public Fraction() {
        // Setting numerator to 0.
        this.numerator = 0;
        // Setting denominator to 1.
        this.denominator = 1;
    }

    // Constructor to make a fraction from given numbers.
    public Fraction(long n, long d) {
        // Can't have a fraction with a denominator of zero!
        if (d == 0) {
            throw new IllegalArgumentException("Can't divide by zero!");
        }

        // If only the denominator is negative, switch signs.
        if (d < 0) {
            n = -n; // Make the numerator negative.
            d = -d; // Make the denominator positive.
        }

        // Assign values to numerator and denominator.
        this.numerator = n;
        this.denominator = d;

        // Simplify the fraction to its lowest terms.
        reduce();
    }

    // Finds the largest number that can divide both numerator and denominator.
    private long GCD() {
        // Start with the absolute value of the numerator.
        long gcd = Math.abs(numerator);
        // Take the remainder when dividing the numerator by the denominator.
        long remainder = Math.abs(denominator);

        // Keep finding the remainder until it becomes zero.
        while (remainder != 0) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        // Return the largest common divisor.
        return gcd;
    }

    // Makes the fraction simpler.
    private void reduce() {
        // Find the largest number that can divide both.
        long gcd = GCD();
        // Divide both parts by that number.
        numerator /= gcd;
        denominator /= gcd;

        // If the denominator became negative, switch signs.
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // Adds two fractions.
    public Fraction plus(Fraction rhs) {
        // Cross-multiply and add to get the new numerator.
        long n = this.numerator * rhs.denominator + this.denominator * rhs.numerator;
        // Multiply denominators for the new denominator.
        long d = this.denominator * rhs.denominator;
        // Return the new fraction.
        return new Fraction(n, d);
    }

    // Subtracts one fraction from another.
    public Fraction minus(Fraction rhs) {
        // Cross-multiply and subtract for the new numerator.
        long n = this.numerator * rhs.denominator - this.denominator * rhs.numerator;
        // Multiply denominators for the new denominator.
        long d = this.denominator * rhs.denominator;
        // Return the new fraction.
        return new Fraction(n, d);
    }

    // Multiplies two fractions.
    public Fraction times(Fraction rhs) {
        // Multiply numerators for the new numerator.
        long n = this.numerator * rhs.numerator;
        // Multiply denominators for the new denominator.
        long d = this.denominator * rhs.denominator;
        // Return the new fraction.
        return new Fraction(n, d);
    }

    // Divides one fraction by another.
    public Fraction dividedBy(Fraction rhs) {
        // Multiply the numerator by the other fraction's denominator.
        long n = this.numerator * rhs.denominator;
        // Multiply the denominator by the other fraction's numerator.
        long d = this.denominator * rhs.numerator;
        // Return the new fraction.
        return new Fraction(n, d);
    }

    // Flips the fraction upside down.
    public Fraction reciprocal() {
        // Switch numerator and denominator.
        return new Fraction(this.denominator, this.numerator);
    }

    // Converts the fraction to a string format.
    @Override
    public String toString() {
        // Return as "numerator/denominator".
        return this.numerator + "/" + this.denominator;
    }

    // Converts the fraction to a decimal number.
    public double toDouble() {
        return (double) this.numerator / this.denominator;
    }

    @Override
    public int compareTo(Fraction other) {
        double difference = this.toDouble() - other.toDouble();
        if (difference > 0) return 1;
        if (difference < 0) return -1;
        return 0;
    }
}