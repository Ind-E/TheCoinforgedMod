package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.tempCards.GreenFive;
import CoinforgedPackage.cards.tempCards.GreenFour;
import CoinforgedPackage.cards.tempCards.GreenOne;
import CoinforgedPackage.cards.tempCards.GreenSix;
import CoinforgedPackage.cards.tempCards.GreenThree;
import CoinforgedPackage.cards.tempCards.GreenTwo;
import CoinforgedPackage.powers.SnakeEyesPower;

public class RollGreenAction extends RollBaseAction {

    public RollGreenAction(int magic) {
        super(magic);
    }

    public RollGreenAction(int magic, int minroll, int maxroll) {
        super(magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int str_to_lose;
        if (player.hasPower(SnakeEyesPower.POWER_ID)) {
            str_to_lose = 1;
        } else {
            str_to_lose = AbstractDungeon.cardRandomRng.random(minroll, maxroll);
        }

        AbstractCard cardToAdd = null;
        switch (str_to_lose) {
            case 1:
                cardToAdd = new GreenOne();
                break;
            case 2:
                cardToAdd = new GreenTwo();
                break;
            case 3:
                cardToAdd = new GreenThree();
                break;
            case 4:
                cardToAdd = new GreenFour();
                break;
            case 5:
                cardToAdd = new GreenFive();
                break;
            case 6:
                cardToAdd = new GreenSix();
                break;
            default:
                cardToAdd = new GreenSix(str_to_lose);
                break;
        }
        return cardToAdd;
    }
}
