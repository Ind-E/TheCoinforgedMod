package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Indebt extends AbstractCoinforgedCard {
    private static final int MAGIC = 12;
    private static final int UPG_MAGIC = 3;
    private static final int WEAK = 2;
    private static final int UPG_WEAK = 1;

    public static final String ID = makeID(Indebt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2);

    public Indebt() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("W", WEAK, UPG_WEAK);
    }

    // public Indebt(ChipColor chip) {
    // super(ID, info, chip);
    // setDamage(DAMAGE, UPG_DAMAGE);
    // setMagic(MAGIC, UPG_MAGIC);
    // }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(m, p, new CripplingDebtPower(m, this.magicNumber)));
        Wiz.atb(new ApplyPowerAction(m, p, new WeakPower(m, customVar("W"), false)));
    }
}