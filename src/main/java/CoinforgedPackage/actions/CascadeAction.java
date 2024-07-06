package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class CascadeAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private int cost;
    private boolean freeToPlayOnce;

    public CascadeAction(int cost, boolean freeToPlayOnce) {
        this.cost = cost;
        this.freeToPlayOnce = freeToPlayOnce;
    }

    public void update() {
        if (!freeToPlayOnce) {
            for (AbstractCard card : p.drawPile.group) {
                if (card.costForTurn < cost && card.costForTurn >= 0) {
                    playCard(card);
                    isDone = true;
                    return;
                }
            }
        }

        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                AbstractDungeon.player.dialogY, 3.0F, "No target found", true));
        isDone = true;

    }

    private void playCard(AbstractCard card) {
        target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        AbstractDungeon.player.drawPile.group.remove(card);
        AbstractDungeon.getCurrRoom().souls.remove(card);
        AbstractDungeon.player.limbo.group.add(card);
        card.current_y = -200.0F * Settings.scale;
        card.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
        card.target_y = (float) Settings.HEIGHT / 2.0F;
        card.targetAngle = 0.0F;
        card.lighten(false);
        card.drawScale = 0.12F;
        card.targetDrawScale = 0.75F;
        card.applyPowers();
        addToTop(new NewQueueCardAction(card, target, false, true));
        addToTop(new UnlimboAction(card));
        if (!Settings.FAST_MODE) {
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        } else {
            addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
        }
    }
}