import java.time.LocalDateTime;

public class RePurchasePointPayment {
    RePurchasePointPayment(final Customer customer, final Comic comic) {
        if (!customer.isEnabled()) {
            throw new IllegalArgumentException("유효하지 않은 계정입니다");
        }
        if (!comic.isEnabled()) {
            throw new IllegalArgumentException("현재 구매할 수 없는 만화입니다.");
        }
        if (customer.isShortOfPoint(commic)) {
            throw new RuntimeException("보유하고 있는 포인트가 부족합니다.");
        }

        customerId = customer.id;
        comicId = comic.id;
        consumptionPoint = comic.currentPurchasePoint;
        paymentDateTime = LocalDateTime.now();
    }
}
