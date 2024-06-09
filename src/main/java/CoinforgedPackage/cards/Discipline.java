package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.ModifiedHandSizePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class Discipline extends AbstractCoinforgedCard {
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(Discipline.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2);

    public Discipline() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
        Wiz.applyToSelf(new DexterityPower(p, magicNumber));
        Wiz.applyToSelf(new ModifiedHandSizePower(4 - BaseMod.MAX_HAND_SIZE)); // set max hand size to 4
    }

}
