package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class buffBlockModifier extends AbstractCardModifier {
    private int buff;

    public buffBlockModifier(int buff) {
        this.buff = buff;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new buffBlockModifier(buff);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (card.baseBlock > 0) {
            card.baseBlock += buff;
            card.applyPowers();
        }
    }

}
