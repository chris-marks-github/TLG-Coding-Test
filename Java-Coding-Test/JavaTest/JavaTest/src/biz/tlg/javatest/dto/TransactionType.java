package biz.tlg.javatest.dto;

/**
 * 
 * @author paul.manning
 * 
 */
public enum TransactionType {


    PURCHASE("purchase"), PURCHASE_N_CASHBACK("purchase_n_cashback"), CASH("cash"), REFUND("refund");

    private final String type;

    private TransactionType(String type) {

        this.type = type;
    }

    public static TransactionType getTransactionType(String type) {

        for (TransactionType it : TransactionType.values()) {
            if (it.type.equalsIgnoreCase(type)) {
                return it;
            }
        }
        return null;
    }

    public String getType() {

        return type;
    }

    public String toString() {

        return type;
    }

}
