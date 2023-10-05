 public class Main {
        public static void main(String[] args) {
            Fraction fraction1= new Fraction(8, 12);
            Fraction fraction2= new Fraction(2, 8);
            System.out.println("Fraction 1 is :" + fraction1);
            System.out.println("Fraction 2 is :" + fraction2);
            System.out.println("sum is : "+ fraction1.plus(fraction2));
            System.out.println("if Fraction 1 minus Fraction 2:"+ fraction1.minus(fraction2) );
            System.out.println("if Fraction 1 times Fraction 2:"+ fraction1.times(fraction2));
            System.out.println("if Fraction 1 dividedBy Fraction 2:"+ fraction1.dividedBy(fraction2));
            System.out.println("reciprocal:" + fraction1.reciprocal()+ " " + fraction2.reciprocal());
            System.out.println("change to string :" + fraction1.toString()+" " + fraction2.toString());
            System.out.println("Double value:"+ fraction1.toDouble()+ " " + fraction2.toDouble() );
//            try {
//                Rainfall rainData = new Rainfall("rainfall_data.txt");
//                rainData.generateReport();
//            } catch (Exception e) {
//                System.out.println("Error: " + e.getMessage());
//            }
        }
}

