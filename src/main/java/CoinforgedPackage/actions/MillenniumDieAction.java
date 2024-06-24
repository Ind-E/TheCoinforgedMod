package CoinforgedPackage.actions;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.MillenniumDie;
import CoinforgedPackage.cards.tempCards.ChaosFive;
import CoinforgedPackage.cards.tempCards.ChaosFour;
import CoinforgedPackage.cards.tempCards.ChaosOne;
import CoinforgedPackage.cards.tempCards.ChaosSix;
import CoinforgedPackage.cards.tempCards.ChaosThree;
import CoinforgedPackage.cards.tempCards.ChaosTwo;
import CoinforgedPackage.util.Wiz;

public class MillenniumDieAction extends AbstractGameAction {
    public static final String[] TEXT;

    public MillenniumDieAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == startDuration) {
            CardGroup temp = new CardGroup(CardGroupType.UNSPECIFIED);
            temp.addToTop(new ChaosOne());
            temp.addToTop(new ChaosTwo());
            temp.addToTop(new ChaosThree());
            temp.addToTop(new ChaosFour());
            temp.addToTop(new ChaosFive());
            temp.addToTop(new ChaosSix());

            AbstractDungeon.gridSelectScreen.open(temp, 1, TEXT[0], false);
            tickDuration();
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator<AbstractCard> var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (var1.hasNext()) {
                    AbstractCard c = (AbstractCard) var1.next();
                    Wiz.atb(new MakeTempCardInHandAction(c, 1));
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            tickDuration();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString(MillenniumDie.ID).TEXT;
    }

}