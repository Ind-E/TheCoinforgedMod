package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.GoForBrokePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class GoForBroke extends AbstractCoinforgedCard{
    private static final int MAGIC = 1;

    public static final String ID = makeID(GoForBroke.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        3
    );

    public GoForBroke() {
        super(ID, info);
        setMagic(MAGIC);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new GoForBrokePower(p, this.magicNumber), this.magicNumber));
    }
}