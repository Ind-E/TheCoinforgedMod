package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.FloodgatePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Floodgate extends AbstractCoinforgedCard {
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(Floodgate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public Floodgate() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FloodgatePower(magicNumber));
    }

}