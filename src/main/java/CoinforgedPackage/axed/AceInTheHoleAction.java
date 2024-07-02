package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import basemod.BaseMod;

public class AceInTheHoleAction extends SeekAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean upgraded;

    public AceInTheHoleAction(int amount, boolean upgraded) {
        super(amount);
        this.p = AbstractDungeon.player;
        this.upgraded = upgraded;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_MED) {
            if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.createHandIsFullDialog();
                isDone = true;
                return;
            }

            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);

            for (AbstractCard c : p.drawPile.group) {
                tmp.addToRandomSpot(c);
            }

            if (upgraded)
                for (AbstractCard c : p.exhaustPile.group) {
                    tmp.addToRandomSpot(c);
                }

            AbstractCard card;
            if (tmp.size() == 0) {
                isDone = true;
            } else if (tmp.size() == 1) {
                card = tmp.getTopCard();
                if (p.hand.size() == 10) {
                    p.drawPile.moveToDiscardPile(card);
                    p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    if (p.drawPile.contains(card))
                        p.drawPile.removeCard(card);
                    else if (p.exhaustPile.contains(card))
                        p.exhaustPile.removeCard(card);

                    card.setCostForTurn(0);
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }

                isDone = true;
            } else if (tmp.size() <= amount) {
                for (int i = 0; i < tmp.size(); ++i) {
                    card = tmp.getNCardFromTop(i);
                    if (p.hand.size() == 10) {
                        p.drawPile.moveToDiscardPile(card);
                        p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        if (p.drawPile.contains(card))
                            p.drawPile.removeCard(card);
                        else if (p.exhaustPile.contains(card))
                            p.exhaustPile.removeCard(card);
                        card.setCostForTurn(0);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }
                }

                isDone = true;
            } else {
                if (amount == 1) {
                    AbstractDungeon.gridSelectScreen.open(tmp, amount, TEXT[0], false);
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp, amount, TEXT[1], false);
                }

                tickDuration();
            }
        } else

        {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {

                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    c.unhover();
                    if (p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                        p.drawPile.moveToDiscardPile(c);
                        p.createHandIsFullDialog();
                    } else {
                        if (p.drawPile.contains(c))
                            p.drawPile.removeCard(c);
                        else if (p.exhaustPile.contains(c))
                            p.exhaustPile.removeCard(c);
                        c.setCostForTurn(0);
                        p.hand.addToTop(c);
                    }

                    p.hand.refreshHandLayout();
                    p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                p.hand.refreshHandLayout();
            }

            tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
        TEXT = uiStrings.TEXT;
    }
}
