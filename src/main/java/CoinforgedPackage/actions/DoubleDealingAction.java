package CoinforgedPackage.actions;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DoubleDealingAction extends AbstractGameAction {

    private AbstractPlayer p = AbstractDungeon.player;
    private String prompt = "swap costs.";
    private List<AbstractCard> chosenCards;

    public DoubleDealingAction() {
        super();
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        for (int i = 0; i < p.hand.size(); i++) {
            AbstractCard card = p.hand.group.get(i);
            card.baseHeal = i;
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty() || this.p.hand.size() == 1) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(prompt, 2, false, false, false, false, false);
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            chosenCards = new ArrayList<AbstractCard>();
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.chosenCards.add(c);
            }
            if (this.chosenCards.size() == 2) {
                AbstractCard card1 = this.chosenCards.get(0);
                AbstractCard card2 = this.chosenCards.get(1);
                int temp = card1.costForTurn;

                card1.cost = card1.costForTurn = card2.costForTurn;
                card2.cost = card2.costForTurn = temp;
                if (card1.freeToPlayOnce || card2.freeToPlayOnce) {
                    card1.freeToPlayOnce = !card1.freeToPlayOnce;
                    card2.freeToPlayOnce = !card2.freeToPlayOnce;
                }

                card1.unhover();
                card1.lighten(true);
                card1.setAngle(0.0F);
                card1.drawScale = 0.12F;
                card1.targetDrawScale = 0.75F;
                card1.current_x = CardGroup.DRAW_PILE_X;
                card1.current_y = CardGroup.DRAW_PILE_Y;

                card2.unhover();
                card2.lighten(true);
                card2.setAngle(0.0F);
                card2.drawScale = 0.12F;
                card2.targetDrawScale = 0.75F;
                card2.current_x = CardGroup.DRAW_PILE_X;
                card2.current_y = CardGroup.DRAW_PILE_Y;

                card1.untip();
                card2.untip();
                if (card1.baseHeal < card2.baseHeal) {
                    p.hand.group.add(card1.baseHeal, card1);
                    p.hand.group.add(card2.baseHeal, card2);
                } else {
                    p.hand.group.add(card2.baseHeal, card2);
                    p.hand.group.add(card1.baseHeal, card1);
                }

                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                this.isDone = true;
            }
        }
        tickDuration();
    }

}
