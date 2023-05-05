package ee.taltech.iti0202.kt2.chocolatefactory;


public class ChocolateFactory {
    private int chocolateBoxesMade;
    private int costSoFar;

    /**
     * Chocolate factory constructor.
     */
    public ChocolateFactory() {
        chocolateBoxesMade = 0;
        costSoFar = 0;
    }

    public enum BoxType {

        SQUARE1(4, 4),
        SQUARE2(5, 5),
        RECTANGLE1(3, 6),
        RECTANGLE2(4, 8);

        private int width;
        private int length;

        BoxType(int width, int length) {
            this.width = width;
            this.length = length;
        }
    }

    /**
     * Make a box of chocolate.
     * Two chocolate types are given: chocolate1, chocolate2.
     * The box size can be taken from BoxType:
     * length indicates the first index,
     * width indicates the second index in the box.
     *
     * So, BoxType.RECTANGLE1 should be an array (preferredChocolate1Count = 4, wee below):
     * [[1, 2, 1],
     *  [2, 2, 2],
     *  [2, 2, 2],
     *  [2, 2, 2],
     *  [2, 2, 2],
     *  [1, 2, 1]]
     *
     * Depending on the preferredChocolate1Count, different boxes should be created.
     *
     * In case it is 0:
     * The box will contain only chocolate2 instances.
     * For example 4x4 box:
     * [[2, 2, 2, 2],
     *  [2, 2, 2, 2],
     *  [2, 2, 2, 2],
     *  [2, 2, 2, 2]]
     *
     * In case it is 4:
     * The corners contain chocolate 1 instance, the rest is chocolate2.
     * For example 4x4 box:
     * [[1, 2, 2, 1],
     *  [2, 2, 2, 2],
     *  [2, 2, 2, 2],
     *  [1, 2, 2, 1]]
     *
     * In case it is the number of chocolates on the edges:
     * The edge contains chocolate1 instances, the rest is chocolate2.
     * For example 4x4 box (preferredChocolate1Count = 12 in that case):
     * [[1, 1, 1, 1],
     *  [1, 2, 2, 1],
     *  [1, 2, 2, 1],
     *  [1, 1, 1, 1]]
     */
    public ChocolateType[][] makeChocolateBox(
        ChocolateType chocolate1,
        ChocolateType chocolate2,
        Integer preferredChocolate1Count,
        BoxType boxType
    ) {
        chocolateBoxesMade++;
        ChocolateType[][] chocolates = new ChocolateType[boxType.length][boxType.width];
        for (int i = 0; i < boxType.length; i++) {
            for (int j = 0; j < boxType.width; j++) {
                chocolates[i][j] = chocolate2;
                costSoFar += chocolate2.getPricePerPiece();
            }
        }
        if (preferredChocolate1Count == 0) {
            return chocolates;
        }
        if (preferredChocolate1Count == 4) {
            chocolates[0][0] = chocolate1;
            chocolates[0][boxType.width - 1] = chocolate1;
            chocolates[boxType.length - 1][0] = chocolate1;
            chocolates[boxType.length - 1][boxType.width - 1] = chocolate1;
            costSoFar += 4 * (chocolate1.getPricePerPiece() - chocolate2.getPricePerPiece());
            return chocolates;
        }
        if (preferredChocolate1Count == 2 * (boxType.length + boxType.width) - 4) {
            for (int j = 0; j < boxType.width; j++) {
                chocolates[0][j] = chocolate1;
                chocolates[boxType.length - 1][j] = chocolate1;
                costSoFar += 2 * (chocolate1.getPricePerPiece() - chocolate2.getPricePerPiece());
            }
            for (int i = 0; i < boxType.length; i++) {
                chocolates[i][0] = chocolate1;
                chocolates[i][boxType.width - 1] = chocolate1;
                costSoFar += 2 * (chocolate1.getPricePerPiece() - chocolate2.getPricePerPiece());
            }
            costSoFar -= 4 * (chocolate1.getPricePerPiece() - chocolate2.getPricePerPiece());
            return chocolates;
        }
        for (int i = 0; i < boxType.length; i++) {
            for (int j = 0; j < boxType.width; j++) {
                if ((i + j) % 2 == 0) {
                    chocolates[i][j] = chocolate1;
                    costSoFar += chocolate1.getPricePerPiece() - chocolate2.getPricePerPiece();
                }

            }
        }
        return chocolates;
    }

    public int getChocolateBoxesMade() {
        return chocolateBoxesMade;
    }

    public int getCostSoFar() {
        return costSoFar;
    }

    public static void main(String[] args) {
        ChocolateFactory factory = new ChocolateFactory();
        ChocolateType[][] a = factory.makeChocolateBox(new ChocolateType(7, "0"),
            new ChocolateType(9, "o"), 4, BoxType.RECTANGLE1);
        System.out.println(factory.getCostSoFar());
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j].getStringForm());
            }
            System.out.println();
        }
        // first row should be: 0o0
    }
}
