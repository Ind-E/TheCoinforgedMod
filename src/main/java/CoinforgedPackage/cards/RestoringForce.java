package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.OverflowAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.ModifiedHandSizePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class RestoringForce extends AbstractCoinforgedCard {
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 2;

    public static final String ID = makeID(RestoringForce.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public RestoringForce() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, block, true));
        Wiz.atb(new GainBlockAction(p, block, true));
        Wiz.atb(new OverflowAction(new ApplyPowerAction(p, p, new ModifiedHandSizePower(magicNumber))));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (Wiz.adp().hand.size() >= BaseMod.MAX_HAND_SIZE - 1) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
