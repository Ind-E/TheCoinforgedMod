package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public abstract class BlueDieBase extends AbstractCoinforgedCard {
    private static final int UPG_BLOCK = 2;

    public static final String ID = makeID(BlueDieBase.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public BlueDieBase(String ID, int BLOCK) {
        this(ID, info, BLOCK, UPG_BLOCK);
    }

    public BlueDieBase(String ID, CardStats info, int BLOCK, int UPG_BLOCK) {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        this.exhaust = true;
        tags.add(CustomTags.DIE);
        tags.add(CustomTags.BLUE_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, this.block));
    }

}
