import java.util.List;

public class DeliveryManager {
    public static int deliveryCharge(List<Product> products) {
        int charge = 0;
        int totalPrice = 0;
        for (Product each : products) {
            totalPrice += each.price;
        }
        if (totalPrice < 20000) {
            charge = 5000;
        } else {
            charge = 0;
        }
        return charge;
    }
}
