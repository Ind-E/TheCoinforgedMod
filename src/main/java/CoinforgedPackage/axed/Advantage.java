package CoinforgedPackage.axed;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.AdvantagePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Advantage extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;

    public static final String ID = makeID(Advantage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Advantage() {
        super(ID, info);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new AdvantagePower(p, this.magicNumber)));
        if (upgraded) {
            Wiz.atb(new DrawCardAction(1));
        }
    }

    public AbstractCard makeCopy() {
        return new Advantage();
    }

}
