package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class ChaosOne extends AbstractCoinforgedCard {
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(ChaosOne.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public ChaosOne() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(CustomTags.MAGIC_DIE);
        tags.add(CustomTags.DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }

}
