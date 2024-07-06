package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import CoinforgedPackage.util.Wiz;

public class UnleashedStrategyAction extends AbstractGameAction {
    public UnleashedStrategyAction() {
        super();
    }

    public void update() {
        for (AbstractCard c : Wiz.adp().hand.group) {
            if (c.costForTurn >= 1) {
                c.setCostForTurn(c.costForTurn - 1);
            }
        }
        isDone = true;
    }
}