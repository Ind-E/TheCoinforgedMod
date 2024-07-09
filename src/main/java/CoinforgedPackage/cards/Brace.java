package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.MinHandSizePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Brace extends AbstractCoinforgedCard {
    private static final int MAGIC = 6;

    public static final String ID = makeID(Brace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public Brace() {
        super(ID, info);
        setMagic(MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new MinHandSizePower(magicNumber));
    }
}
