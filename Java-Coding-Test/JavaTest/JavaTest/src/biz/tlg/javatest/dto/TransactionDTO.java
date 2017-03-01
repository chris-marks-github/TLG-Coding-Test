package biz.tlg.javatest.dto;


import static biz.tlg.javatest.utils.Utils.LOG_FMT;

import java.util.Date;
import java.util.Formatter;


/**
 * This class is responsible for holding payment specific information.
 * 
 * @author daniel.webber
 * 
 */
public class TransactionDTO {

    /** Card Details stored in the CardDTO */
    private CardDTO cardDTO;

    /** The type i.e. <TRANSACTION type> */
    private TransactionType transactionType;

    /** The customer present/not present flag i.e. <TRANSACTION customer> */
    private CustomerPresent customerPresent;

    /** The time of the transaction */
    private Date time = null;
    
    /** The amount of the transaction */
    private String amount = null;
    

    /**
     * The PaymentDTO Constructor.
     * 
     */
    public TransactionDTO() {

    }

   

    /**
     * {@inheritDoc}
     * 
     * @return A formatted string that displays all payment specific details for
     *         the transaction.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("Payment Details\n");
        if (this.cardDTO != null) {
            sb.append(this.cardDTO.toString());
        }
        
        if (this.transactionType != null) {
            formatter.format(LOG_FMT, "Transaction Type:", this.transactionType.getType());
        }

        if (this.customerPresent != null) {
            formatter.format(LOG_FMT, "Customer:", this.customerPresent);
        }
        
        if (this.time != null) {
            formatter.format(LOG_FMT, "Time:", this.time);
        }
        
        if (this.amount != null) {
            formatter.format(LOG_FMT, "Amount:", this.amount);
        }
        
        /* Make Eclipse Juno happy by closing the resources */
        String builtString = formatter.toString();
        formatter.close();
        return builtString;
    }

   

    /**
     * 
     * @param cardDTO
     *            The cardDTO code as a {@link cardDTO}.
     */
    public void setCardDTO(CardDTO cardDTO) {

        this.cardDTO = cardDTO;
    }

    /**
     * 
     * @return The CardDTO as a {@link cardDTO}.
     */
    public CardDTO getCardDTO() {

        return this.cardDTO;
    }

    /**
     * 
     * @param tt
     *            The {@link TransactionType} enumerated value.
     */
    public void setTransactionType(TransactionType tt) {

        this.transactionType = tt;
    }

    /**
     * 
     * @return The {@link TransactionType} enumerated value.
     */
    public TransactionType getTransactionType() {

        return this.transactionType;
    }

    /**
     * @param customerPresent
     *            The {@link CustomerPresent} enumerated value.
     */
    public void setCustomerPresent(CustomerPresent customerPresent) {

        this.customerPresent = customerPresent;
    }

    /**
     * 
     * @return The {@link CustomerPresent} enumerated value.
     */
    public CustomerPresent getCustomerPresent() {

        return this.customerPresent;
    }
    
    /**
     * 
     * @param time
     *            The {@link Date} transaction time value.
     */
    public void setTransactionTime(Date time) {

        this.time = time;
    }

    /**
     * 
     * @return The {@link Date} transaction time.
     */
    public Date getTransactionTime() {

        return this.time;
    }
    
    /**
     * 
     * @param amount
     *            The {@link String} transaction amount.
     */
    public void setTransactionAmount(String amount) {

        this.amount = amount;
    }

    /**
     * 
     * @return The {@link String} transaction amount.
     */
    public String getTransactionAmount() {

        return this.amount;
    }
    
}
