package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.HitAndRunPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class HitAndRun extends AbstractCoinforgedCard {

    public static final String ID = makeID(HitAndRun.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2);

    public HitAndRun() {
        super(ID, info);
        this.isEthereal = true;
    }

    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.isEthereal = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(HitAndRunPower.POWER_ID)) {
            Wiz.applyToSelf(new HitAndRunPower(p, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HitAndRun();
    }
}
