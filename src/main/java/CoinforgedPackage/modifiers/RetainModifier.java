package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class RetainModifier extends AbstractCardModifier {
    private boolean originalRetain;

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!originalRetain) {
            return "Retain. NL " + rawDescription;
        }
        return rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RetainModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!card.selfRetain) {
            originalRetain = false;
            card.selfRetain = true;
        } else {
            originalRetain = true;
        }
    }

}
