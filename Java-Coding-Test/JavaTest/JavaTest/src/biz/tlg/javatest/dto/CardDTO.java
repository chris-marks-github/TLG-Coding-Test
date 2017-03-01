package biz.tlg.javatest.dto;

import static biz.tlg.javatest.utils.Utils.LOG_FMT;
import static biz.tlg.javatest.utils.Utils.isNotNullOrEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;

/**
 * Contains all card details associated with a transaction
 * 
 * @author paul.manning
 * 
 */
public class CardDTO {

    /** The card PAN */
    private String pan = "";

    /** The end date of the card */
    private String end = "";

    /** The track 2 data read off the card's magnetic stripe */
    private String track2 = "";

    /** The token(s) that represents the card details */
    private Collection<TokenDTO> tokens = new ArrayList<TokenDTO>();

    /**
     * Constructor for the CardDTO when card details are keyed.
     * 
     * @param pan
     *            The PAN as a {@link String}.
     * @param end
     *            The end date as a {@link String}.
     */
    public CardDTO(String pan, String end) {

        if (!isNotNullOrEmpty(pan)) {
            throw new IllegalArgumentException("PAN must not be null or empty");
        }

        if (!isNotNullOrEmpty(end)) {
            throw new IllegalArgumentException("Expiry date must not be null or empty");
        }

        this.pan = pan;
        this.end = end;

    }

    /**
     * @return the pan
     */
    public String getPan() {

        return this.pan;
    }

    /**
     * @param pan
     *            the pan to set, must not be null
     */
    public void setPan(String pan) {

        this.pan = pan;
    }

    /**
     * @return the end
     */
    public String getExpiryDate() {

        return this.end;
    }

    /**
     * @param expDate
     *            the expDate to set, must not be null
     */
    public void setExpiryDate(String end) {

        this.end = end;
    }

    /**
     * @return the track2
     */
    public String getTrack2() {

        return this.track2;
    }

    /**
     * @param track2
     *            the track2 to set, must not be null
     */
    public void setTrack2(String track2) {

        this.track2 = track2;
    }

    /**
     * @param tokens
     *            the tokens to set
     */
    public void setTokens(Collection<TokenDTO> tokens) {

        this.tokens = tokens;
    }

    /**
     * @return the tokens
     */
    public Collection<TokenDTO> getTokens() {

        return this.tokens;
    }

    /**
     * {@inheritDoc}
     * 
     * @return A formatted string that displays all identifier details for the card details.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Card\n");
        Formatter formatter = new Formatter(sb);

        formatter.format(LOG_FMT, "PAN:", this.pan);
        formatter.format(LOG_FMT, "Expiry Date:",  this.end);
        formatter.format(LOG_FMT, "Track 2:",  this.track2);


        if (tokens != null && tokens.size() > 0) {
            for (TokenDTO token : tokens) {
                sb.append(token.toString());
            }
        }

        /* Make Eclipse Juno happy by closing the resources */
        String builtString = formatter.toString();
        formatter.close();
        return builtString;
    }


}
