package CoinforgedPackage.actions;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import CoinforgedPackage.cards.Grapes;
import CoinforgedPackage.modifiers.GrapesModifier;
import CoinforgedPackage.util.Wiz;
import basemod.helpers.CardModifierManager;

//TODO: don't hardcode strings
public class GrapesAction extends AbstractGameAction {
    private int amount;
    private boolean upgraded;
    private AbstractPlayer p;
    private String msg;
    private List<AbstractCard> cannotBuff = new ArrayList<AbstractCard>();

    public GrapesAction(int amount, boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.isDone = false;
        this.amount = amount;
        this.upgraded = upgraded;
        msg = "Increase Damage and Block by " + this.amount + ".";
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : p.hand.group) {
                if (c.baseDamage <= 0 && c.baseBlock <= 0) {
                    this.cannotBuff.add(c);
                }
            }

            if (this.cannotBuff.size() == this.p.hand.group.size()) {
                this.isDone = true;
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                        AbstractDungeon.player.dialogY, 3.0F, "I have no cards that can be buffed", true));
                return;
            }

            this.p.hand.group.removeAll(this.cannotBuff);

            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(this.msg, 1, false, false, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                CardModifierManager.addModifier(this.p.hand.getTopCard(), new GrapesModifier(this.amount));
                this.p.hand.getTopCard().superFlash();
                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                CardModifierManager.addModifier(c, new GrapesModifier(this.amount));
                c.superFlash();
                c.applyPowers();
                this.p.hand.addToTop(c);
            }

            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : cannotBuff) {
            p.hand.addToTop(c);
            c.superFlash();
        }

        this.p.hand.refreshHandLayout();
        Wiz.atb(new MakeTempCardInHandAction(new Grapes(1, this.upgraded)));
        return;
    }

}