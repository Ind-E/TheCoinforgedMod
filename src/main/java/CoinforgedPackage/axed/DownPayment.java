package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class DownPayment extends AbstractCoinforgedCard {
    private static final int DEBT = 15;
    private static final int UPG_DEBT = 5;

    public static final String ID = makeID(DownPayment.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1);

    public DownPayment() {
        super(ID, info);
        setInnate(true);
        setExhaust(true);
        setCustomVar("debt", DEBT, UPG_DEBT);
        setVarCalculation("debt", debtCalc);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new CripplingDebtPower(m, customVar("debt")));
    }
}