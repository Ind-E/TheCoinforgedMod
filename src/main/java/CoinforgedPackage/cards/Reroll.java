package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.RerollPower;
import CoinforgedPackage.util.CardStats;

public class Reroll extends AbstractCoinforgedCard {

    public static final String ID = makeID(Reroll.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public Reroll() {
        super(ID, info);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(RerollPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new RerollPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Reroll();
    }
}
