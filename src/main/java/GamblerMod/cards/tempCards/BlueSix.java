package GamblerMod.cards.tempCards;

public class BlueSix extends BlueDieBase{
    private static final int BLOCK = 6;

    public static final String ID = makeID(BlueSix.class.getSimpleName());

    public BlueSix() {
        super(ID, BLOCK);
    }

}
