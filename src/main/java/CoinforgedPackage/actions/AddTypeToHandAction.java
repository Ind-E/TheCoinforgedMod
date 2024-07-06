package CoinforgedPackage.actions;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class AddTypeToHandAction extends AbstractGameAction {
    private CardType type;
    private boolean upgraded;

    public AddTypeToHandAction(CardType type, boolean upgraded) {
        super();
        this.type = type;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard cardToDraw;
        List<AbstractCard> possibleCards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.drawPile.group) {
            if (c.type == this.type && !c.hasTag(CustomTags.POKER_CHIP)) {
                possibleCards.add(c);
            }
        }
        if (possibleCards.size() == 0) {
            Wiz.att(new AddTypeToHandAction(this.type, this.upgraded));
            Wiz.att(new EmptyDeckShuffleAction()); // doesn't matter if deck is empty lol
            this.isDone = true;
            return;
        }
        cardToDraw = possibleCards.get(AbstractDungeon.cardRandomRng.random(0, possibleCards.size() - 1));

        if (p.hand.size() >= BaseMod.MAX_HAND_SIZE) {
            p.drawPile.moveToDiscardPile(cardToDraw);
            p.createHandIsFullDialog();
        } else {
            cardToDraw.unhover();
            cardToDraw.lighten(true);
            cardToDraw.setAngle(0.0F);
            cardToDraw.drawScale = 0.12F;
            cardToDraw.targetDrawScale = 0.75F;
            cardToDraw.current_x = CardGroup.DRAW_PILE_X;
            cardToDraw.current_y = CardGroup.DRAW_PILE_Y;
            cardToDraw.setCostForTurn(upgraded ? 0 : cardToDraw.costForTurn - 1);
            p.drawPile.removeCard(cardToDraw);
            AbstractDungeon.player.hand.addToTop(cardToDraw);
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
        }
        this.isDone = true;
    }
}