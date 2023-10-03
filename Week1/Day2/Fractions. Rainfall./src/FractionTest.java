import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {
    @Test
    public void testDefaultConstructor() {
        Fraction fraction = new Fraction();
        assertEquals(0, fraction.getNumerator());
        assertEquals(1, fraction.getDenominator());
    }

    @Test
    public void testParametrizedConstructor() {
        Fraction fraction = new Fraction(4, 6);
        assertEquals(2, fraction.getNumerator());
        assertEquals(3, fraction.getDenominator());
    }

    @Test
    public void testPlus() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction result = fraction1.plus(fraction2);
        assertEquals(5, result.getNumerator());
        assertEquals(6, result.getDenominator());
    }

    @Test
    public void testMinus() {
        Fraction fraction1 = new Fraction(3, 4);
        Fraction fraction2 = new Fraction(1, 4);
        Fraction result = fraction1.minus(fraction2);
        assertEquals(1, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }

    @Test
    public void testTimes() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(2, 3);
        Fraction result = fraction1.times(fraction2);
        assertEquals(1, result.getNumerator());
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testDividedBy() {
        Fraction fraction1 = new Fraction(2, 3);
        Fraction fraction2 = new Fraction(4, 5);
        Fraction result = fraction1.dividedBy(fraction2);
        assertEquals(5, result.getNumerator());
        assertEquals(6, result.getDenominator());
    }

    // 添加更多测试用例来验证其他方法和性质
}






