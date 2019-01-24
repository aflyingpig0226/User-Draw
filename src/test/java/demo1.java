import java.math.BigDecimal;

public class demo1 {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(12.11111111).setScale(3, 4);
        System.out.println(bigDecimal);
    }
}
