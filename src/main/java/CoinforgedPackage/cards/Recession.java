package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Recession extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public static final String ID = makeID(Recession.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            1);

    public Recession() {
        super(ID, info);
        setMagic(MAGIC);
        setCustomVar("playerWeak", MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            Wiz.applyToEnemy(mo, new WeakPower(mo, magicNumber, false));
        }
        Wiz.applyToSelf(new WeakPower(p, customVar("playerWeak"), false));
    }

}