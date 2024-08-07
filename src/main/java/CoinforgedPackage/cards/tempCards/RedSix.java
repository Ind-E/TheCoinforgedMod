package CoinforgedPackage.cards.tempCards;

public class RedSix extends RedDieBase {
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedSix.class.getSimpleName());

    public RedSix() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedSix(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }

    public RedSix(int dmg) {
        super(ID, dmg, UPG_DAMAGE);
    }

    public RedSix(int dmg, boolean redKingActive) {
        super(ID, info_all, dmg, UPG_DAMAGE, redKingActive);
    }

}
