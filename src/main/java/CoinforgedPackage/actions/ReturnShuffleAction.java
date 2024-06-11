package CoinforgedPackage.actions;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ReturnShuffleAction extends AbstractGameAction {
    private boolean shuffled = false;
    private boolean vfxDone = false;
    private int count = 0;
    private int toReturn = 0;

    public ReturnShuffleAction(int toReturn) {
        setValues(null, null, 0);
        this.toReturn = toReturn;
        actionType = ActionType.SHUFFLE;

        for (AbstractRelic r : AbstractDungeon.player.relics) {
            r.onShuffle();
        }
    }

    public void update() {
        if (!shuffled) {
            shuffled = true;
            AbstractDungeon.player.discardPile.shuffle(AbstractDungeon.shuffleRng);
        }

        if (!vfxDone) {
            Iterator<AbstractCard> discards = AbstractDungeon.player.discardPile.group.iterator();
            if (discards.hasNext() && count < toReturn) {
                ++count;
                AbstractCard card = discards.next();
                discards.remove();
                if (count < 11) {
                    AbstractDungeon.getCurrRoom().souls.shuffle(card, false);
                } else {
                    AbstractDungeon.getCurrRoom().souls.shuffle(card, true);
                }
                return;
            }
            vfxDone = true;
        }
        isDone = true;
    }

}
