package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.GamblePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class GoldenGamble extends AbstractCoinforgedCard {
    private static final int GOLD_GAIN = 75;
    private static final int UPG_GOLD_GAIN = 25;
    private static final int GOLD_LOSS = 10;

    public static final String ID = makeID(GoldenGamble.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1);

    public GoldenGamble() {
        super(ID, info);
        setMagic(GOLD_GAIN, UPG_GOLD_GAIN);
        setExhaust(true);
        setCustomVar("loseGold", GOLD_LOSS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseGold(customVar("loseGold"));
        Wiz.atb(new ApplyPowerAction(p, p, new GamblePower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GoldenGamble();
    }
}
