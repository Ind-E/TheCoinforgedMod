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
    private static final int DEBT = 14;
    private static final int UPG_DEBT = 4;
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
        setMagic(WEAK, UPG_WEAK);
        setCustomVar("debt", DEBT, UPG_DEBT);
        setVarCalculation("debt", debtCalc);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(m, p, new CripplingDebtPower(m, customVar("debt"))));
        Wiz.atb(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
    }
}