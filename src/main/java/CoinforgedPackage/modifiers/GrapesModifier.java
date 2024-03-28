package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class GrapesModifier extends AbstractCardModifier {
    private int amount;

    public GrapesModifier(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (card.baseDamage > 0) {
            card.baseDamage += this.amount;
        }
        if (card.baseBlock > 0) {
            card.baseBlock += this.amount;
        }
        card.applyPowers();
    }

    @Override
    public GrapesModifier makeCopy() {
        return new GrapesModifier(this.amount);
    }

}
