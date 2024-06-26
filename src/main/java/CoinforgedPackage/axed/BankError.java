package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class BankError extends AbstractCoinforgedCard {
    private static final int DEBT = 11;
    private static final int UPG_DEBT = 4;

    public static final String ID = makeID(BankError.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public BankError() {
        super(ID, info);
        setCustomVar("debt", DEBT, UPG_DEBT);
        setVarCalculation("debt", debtCalc);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL)));
        Wiz.applyToEnemy(m, new CripplingDebtPower(m, customVar("debt")));

    }
}