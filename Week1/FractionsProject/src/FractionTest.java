import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

// This class tests the Fraction class to make sure it works as expected.
public class FractionTest {

    // Test 1: Check if the default fraction is set to 0/1.
    @Test
    public void testDefaultConstructor() {
        // Create a new default fraction.
        Fraction f = new Fraction();
        // Check if the fraction is "0/1".
        Assertions.assertEquals("0/1", f.toString());
    }

    // Test 2: Check if a fraction created with specific numbers is represented correctly.
    @Test
    public void testParameterizedConstructor() {
        // Create a fraction with numerator 3 and denominator 4.
        Fraction f = new Fraction(3, 4);
        // Check if the fraction is "3/4".
        Assertions.assertEquals("3/4", f.toString());
    }

    // Test 3: Check if the fraction is simplified upon creation.
    @Test
    public void testReductionInConstructor() {
        // Create a fraction that should be simplified (like 4/8).
        Fraction f = new Fraction(4, 8);
        // Check if the fraction gets simplified to "1/2".
        Assertions.assertEquals("1/2", f.toString());
    }

    // Test 4: If the denominator is negative, the fraction should adjust so the numerator carries the negative sign.
    @Test
    public void testNegativeDenominator() {
        // Create a fraction with a negative denominator.
        Fraction f = new Fraction(1, -2);
        // Check if the fraction's negative sign is moved to the numerator.
        Assertions.assertEquals("-1/2", f.toString());
    }

    // Test 5: Test the addition operation between two fractions.
    @Test
    public void testAddition() {
        // Create two fractions to add together.
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        // Add the two fractions.
        Fraction result = f1.plus(f2);
        // Check if the result is "5/6".
        Assertions.assertEquals("5/6", result.toString());
    }

    // Test 6: Test the subtraction operation between two fractions.
    @Test
    public void testSubtraction() {
        // Create two fractions.
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 4);
        // Subtract the second fraction from the first.
        Fraction result = f1.minus(f2);
        // Check if the result is "1/4".
        Assertions.assertEquals("1/4", result.toString());
    }

    // Test 7: Test the multiplication operation between two fractions.
    @Test
    public void testMultiplication() {
        // Create two fractions to multiply.
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        // Multiply the two fractions.
        Fraction result = f1.times(f2);
        // Check if the result is "1/6".
        Assertions.assertEquals("1/6", result.toString());
    }

    // Test 8: Test the division operation between two fractions.
    @Test
    public void testDivision() {
        // Create two fractions.
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        // Divide the first fraction by the second.
        Fraction result = f1.dividedBy(f2);
        // Check if the result is "3/2".
        Assertions.assertEquals("3/2", result.toString());
    }

    // Test 9: Check the reciprocal of a fraction.
    @Test
    public void testReciprocal() {
        // Create a fraction.
        Fraction f = new Fraction(2, 3);
        // Find its reciprocal.
        Fraction result = f.reciprocal();
        // Check if the reciprocal is "3/2".
        Assertions.assertEquals("3/2", result.toString());
    }

    // Test 10: Convert a fraction to a double and check its value.
    @Test
    public void testToDouble() {
        // Create a fraction.
        Fraction f = new Fraction(1, 2);
        // Convert it to a double.
        double result = f.toDouble();
        // Check if the result is 0.5.
        Assertions.assertEquals(0.5, result);
    }

    // Test 11: Test the compareTo method.
    @Test
    public void testCompareTo() {
        // Create two fractions.
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);
        // Compare the two fractions.
        Assertions.assertTrue(f1.compareTo(f2) < 0);
        Assertions.assertTrue(f2.compareTo(f1) > 0);
        Assertions.assertTrue(f1.compareTo(f1) == 0);
    }

    // Test 12: Demonstrate sorting of an ArrayList of Fractions.
    @Test
    public void testSorting() {
        // Create an ArrayList of fractions.
        ArrayList<Fraction> fractions = new ArrayList<>();
        fractions.add(new Fraction(1, 2));
        fractions.add(new Fraction(2, 3));
        fractions.add(new Fraction(1, 4));
        // Sort the ArrayList using Collections.sort.
        Collections.sort(fractions);
        // Verify the order of fractions after sorting.
        Assertions.assertEquals("1/4", fractions.get(0).toString());
        Assertions.assertEquals("1/2", fractions.get(1).toString());
        Assertions.assertEquals("2/3", fractions.get(2).toString());
    }
}
