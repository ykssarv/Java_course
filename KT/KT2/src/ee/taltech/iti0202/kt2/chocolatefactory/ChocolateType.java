
package ee.taltech.iti0202.kt2.chocolatefactory;
public class ChocolateType {

    private int pricePerPiece;
    private String stringForm;
    /**
     * Create a chocolate piece with the given piece per price and string form.
     */
    public ChocolateType(int pricePerPiece, String stringForm) {
        this.pricePerPiece = pricePerPiece;
        this.stringForm = stringForm;
    }

    /**
     * Get price for this piece.
     */
    public int getPricePerPiece() {
        return pricePerPiece;
    }

    /**
     * Get string form for the piece.
     */
    public String getStringForm() {
        return stringForm;
    }
}
