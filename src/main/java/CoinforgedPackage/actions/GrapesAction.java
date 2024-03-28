package CoinforgedPackage.actions;

import java.util.ArrayList;
import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import CoinforgedPackage.cards.Grapes;
import CoinforgedPackage.modifiers.GrapesModifier;
import basemod.helpers.CardModifierManager;

public class GrapesAction extends AbstractGameAction {
    private int amount;
    private int cost;
    private boolean upgraded;
    private AbstractPlayer p;
    private String msg;
    private ArrayList<AbstractCard> cannotBuff = new ArrayList<AbstractCard>();

    public GrapesAction(int amount, int cost, boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.isDone = false;
        this.amount = amount;
        this.cost = cost;
        this.upgraded = upgraded;
        msg = "Increase Damage and Block by " + this.amount + ".";
    }

    public void update() {
        Iterator<AbstractCard> var1;
        AbstractCard c;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            var1 = this.p.hand.group.iterator();

            while (var1.hasNext()) {
                c = var1.next();
                if (c.baseDamage <= 0 && c.baseBlock <= 0) {
                    this.cannotBuff.add(c);
                }
            }

            if (this.cannotBuff.size() == this.p.hand.group.size()) {
                this.isDone = true;
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                        AbstractDungeon.player.dialogY, 3.0F, "I have no cards that can be buffed", true));
                addToBot(new MakeTempCardInHandAction(new Grapes(this.cost, this.upgraded)));
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
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (var1.hasNext()) {
                c = var1.next();
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
        Iterator<AbstractCard> var1 = this.cannotBuff.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            this.p.hand.addToTop(c);
            c.superFlash();
        }

        this.p.hand.refreshHandLayout();
        addToBot(new MakeTempCardInHandAction(new Grapes(this.cost + 1, this.upgraded)));
        return;
    }

}