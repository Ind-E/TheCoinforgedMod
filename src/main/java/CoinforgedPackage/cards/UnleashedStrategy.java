package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.UnleashedStrategyAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class UnleashedStrategy extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(UnleashedStrategy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1);

    public UnleashedStrategy() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(p, magicNumber));
        Wiz.atb(new UnleashedStrategyAction());
    }
}