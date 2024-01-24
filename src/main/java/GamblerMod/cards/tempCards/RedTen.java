package GamblerMod.cards.tempCards;

public class RedTen extends RedDieBase{
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedTen.class.getSimpleName());

    public RedTen() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedTen(int dmg) {
        super(ID, dmg, UPG_DAMAGE);
    }

}
