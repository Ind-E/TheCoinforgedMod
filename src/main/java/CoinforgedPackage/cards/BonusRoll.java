package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.BonusRollPower;
import CoinforgedPackage.util.CardStats;

public class BonusRoll extends AbstractCoinforgedCard {

    public static final String ID = makeID(BonusRoll.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public BonusRoll() {
        super(ID, info);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(BonusRollPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new BonusRollPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BonusRoll();
    }
}
