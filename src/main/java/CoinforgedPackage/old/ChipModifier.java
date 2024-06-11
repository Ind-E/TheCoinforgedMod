package CoinforgedPackage.old;

import static CoinforgedPackage.util.GeneralUtils.makeKeyword;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.old.AbstractRandomChipCard.ChipColor;
import CoinforgedPackage.old.chips.BaseChip;
import CoinforgedPackage.old.chips.BasicChip;
import CoinforgedPackage.old.chips.BlackChip;
import CoinforgedPackage.old.chips.BlueChip;
import CoinforgedPackage.old.chips.CrackedChip;
import CoinforgedPackage.old.chips.GrayChip;
import CoinforgedPackage.old.chips.GreenChip;
import CoinforgedPackage.old.chips.OrangeChip;
import CoinforgedPackage.old.chips.RedChip;
import basemod.abstracts.AbstractCardModifier;

public class ChipModifier extends AbstractCardModifier {
    private boolean removeOnCardPlayed;
    private AbstractCard prevCardsToPreview;
    private ChipColor color;

    public ChipModifier(ChipColor color, boolean removeOnCardPlayed) {
        this.removeOnCardPlayed = removeOnCardPlayed;
        this.color = color;
    }

    public ChipModifier(ChipColor color) {
        this(color, true);
    }

    public BaseChip colorToChip(ChipColor color) {
        switch (color) {
            case Black:
                return new BlackChip();
            case Blue:
                return new BlueChip();
            case Cracked:
                return new CrackedChip();
            case Gray:
                return new GrayChip();
            case Green:
                return new GreenChip();
            case Orange:
                return new OrangeChip();
            case Red:
                return new RedChip();
            case White:
                return new BasicChip();
            default:
                return null;
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (removeOnCardPlayed) {
            return rawDescription + " NL " + makeKeyword("Initiate:") + " Add a " + makeKeyword(color.toString() + "_Chip") + " into your hand.";
        } else {
            return rawDescription + " NL Add a " + makeKeyword(color.toString() + "_Chip") + " into your hand.";
        }
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(colorToChip(color), 1));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeOnCardPlayed;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        prevCardsToPreview = card.cardsToPreview;
        if (prevCardsToPreview == null) {
            card.cardsToPreview = colorToChip(color);
        }
    }

    @Override
    public void onRemove(AbstractCard card) {
        card.cardsToPreview = prevCardsToPreview;
    }

    @Override
    public ChipModifier makeCopy() {
        return new ChipModifier(color, this.removeOnCardPlayed);
    }
}
