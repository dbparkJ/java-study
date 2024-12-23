import java.time.LocalDateTime;

public class PurchasePointPayment {
    final CustomerId customerId;
    final ComicId comicId;
    final PurchasePoint consumptionPoint;
    final LocalDateTime paymentDateTime;

    PurchasePointPayment(final Customer customer, final Comic comic) {
        if (customer.isEnabled()) {
            customerId = customer.id;
            if (comic.inEnabled()) {
                comicId = comic.id;
                if (comic.currentPurchasePoint.amount <=
                        customer.possessionPoint.amount) {
                    consumptionPoint = comic.currentPurchasePoint;
                    paymentDateTime = LocalDateTime.now();
                } else {
                    throw new RuntimeException("보유하고 있는 포인트가 부족합니다.");
                }
            } else {
                throw new IllegalArgumentException("현재 구매할 수 없는 만화입니다.");
            }
        } else {
            throw new IllegalArgumentException("유효하지 않은 계정입니다.");
        }

    }
}
