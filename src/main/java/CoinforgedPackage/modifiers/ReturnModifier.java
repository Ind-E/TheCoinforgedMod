package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.actions.ReturnShuffleAction;
import CoinforgedPackage.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;

public class ReturnModifier extends AbstractCardModifier implements AlternateCardCostModifier {

    @Override
    public int getAlternateResource(AbstractCard card) {
        return AbstractDungeon.player.discardPile.size();
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int cost) {
        Wiz.att(new ReturnShuffleAction(cost));
        return 0;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return false;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ReturnModifier();
    }

}
