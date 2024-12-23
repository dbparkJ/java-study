public class Customer {
    final CustomerId id;
    final PurchasePoint possessionPoint;

    /**
     * @param comic 구매 대상 웹툰
     * @return 보유 포인트가 부족하다면 true
     */
    boolean isShortOfPoint(Comic comic) {
        return possessionPoint.amout < comic.currentPurchasePoint.amount;
    }
}
