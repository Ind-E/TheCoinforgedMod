package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SetCostFollowUpAction extends AbstractGameAction {

    private int magic = -1;
    private boolean reduce;

    public SetCostFollowUpAction(int magic, boolean reduce) {
        this.magic = magic;
        this.duration = 0.001F;
        this.reduce = reduce;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        tickDuration();
        if (this.isDone) {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if ((!reduce && magic < c.costForTurn) || c.costForTurn > 0)
                    c.setCostForTurn(reduce ? c.costForTurn - magic : magic);
            }
        }

    }

}
