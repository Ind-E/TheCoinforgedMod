package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.util.Wiz;

public class DiscardDrawAction extends AbstractGameAction {
   private int modifier;

   public DiscardDrawAction(int modifier) {
      this.target = AbstractDungeon.player;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
      this.modifier = modifier;
   }

   public void update() {
      if (duration == Settings.ACTION_DUR_FAST) {
         int count = AbstractDungeon.player.hand.size();
         if (count + modifier > 0) {
            Wiz.att(new DrawCardAction(target, count - 1));
            Wiz.att(new DiscardAction(target, target, count, true));
         }
         isDone = true;
      }

   }
}