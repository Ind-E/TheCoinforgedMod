package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class AllIn extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(AllIn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public AllIn() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true, false);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new IntangiblePlayerPower(p, magicNumber));
        Wiz.applyToSelf(new StrengthPower(p, -magicNumber));
        Wiz.applyToSelf(new DexterityPower(p, -magicNumber));
        Wiz.applyToSelf(new WeakPower(p, magicNumber, false));
        Wiz.applyToSelf(new FrailPower(p, magicNumber, false));
        Wiz.applyToSelf(new VulnerablePower(p, magicNumber, false));
    }
}