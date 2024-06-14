package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Cripple extends AbstractCoinforgedCard {
    private static final int DEBT = 7;
    private static final int UPG_DEBT = 3;

    public static final String ID = makeID(Cripple.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1);

    public Cripple() {
        super(ID, info);
        setCustomVar("debt", DEBT, UPG_DEBT);
        setVarCalculation("debt", debtCalc);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            Wiz.atb(new ApplyPowerAction(mo, p, new CripplingDebtPower(mo, customVar("debt"))));
        }
    }
}