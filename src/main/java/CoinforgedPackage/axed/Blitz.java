package CoinforgedPackage.axed;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.BlitzPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Blitz extends AbstractCoinforgedCard{


    public static final String ID = makeID(Blitz.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        2
    );

    public Blitz() {
        super(ID, info);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new NoDrawPower(p)));
        Wiz.atb(new ApplyPowerAction(p, p, new BlitzPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Blitz();
    }
}