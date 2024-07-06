package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.ModifiedHandSizePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Condense extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int HAND_SIZE_DOWN = 3;

    public static final String ID = makeID(Condense.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Condense() {
        super(ID, info);
        setMagic(MAGIC);
        setCustomVar("hand", HAND_SIZE_DOWN);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainEnergyAction(magicNumber));
        Wiz.applyToSelf(new ModifiedHandSizePower(-customVar("hand")));
    }
}