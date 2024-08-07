package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.tempCards.ChaosFive;
import CoinforgedPackage.cards.tempCards.ChaosFour;
import CoinforgedPackage.cards.tempCards.ChaosOne;
import CoinforgedPackage.cards.tempCards.ChaosSix;
import CoinforgedPackage.cards.tempCards.ChaosThree;
import CoinforgedPackage.cards.tempCards.ChaosTwo;

public class ChaosFormAction extends RollBaseAction {

    public ChaosFormAction(AbstractCreature owner) {
        super(1);
    }

    public AbstractCard roll() {
        int chaosRoll = AbstractDungeon.cardRandomRng.random(1, 6);
        AbstractCard cardToAdd = null;
        switch (chaosRoll) {
            case 1:
                cardToAdd = new ChaosOne();
                break;
            case 2:
                cardToAdd = new ChaosTwo();
                break;
            case 3:
                cardToAdd = new ChaosThree();
                break;
            case 4:
                cardToAdd = new ChaosFour();
                break;
            case 5:
                cardToAdd = new ChaosFive();
                break;
            case 6:
                cardToAdd = new ChaosSix();
                break;
        }
        return cardToAdd;
    }
}
