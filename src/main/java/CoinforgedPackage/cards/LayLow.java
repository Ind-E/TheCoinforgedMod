package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class LayLow extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int DISCARD = 1;

    public static final String ID = makeID(LayLow.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1);

    public LayLow() {
        super(ID, info);
        setMagic(MAGIC);
        setBlock(BLOCK, UPG_BLOCK);
        setCustomVar("discard", DISCARD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, block));
        Wiz.atb(new DiscardAction(p, p, customVar("discard"), false));
        Wiz.atb(new DrawCardAction(magicNumber));
    }

}
