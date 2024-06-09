package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class OverflowAction extends AbstractGameAction {
    private AbstractGameAction action;

    public OverflowAction(AbstractGameAction action) {
        super();
        this.action = action;
    }

    public void update() {
        if (AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE - 2) {
            Wiz.att(action);
        }
        this.isDone = true;
    }
}
