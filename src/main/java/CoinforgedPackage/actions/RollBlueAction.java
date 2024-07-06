package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.tempCards.BlueFive;
import CoinforgedPackage.cards.tempCards.BlueFour;
import CoinforgedPackage.cards.tempCards.BlueOne;
import CoinforgedPackage.cards.tempCards.BlueSix;
import CoinforgedPackage.cards.tempCards.BlueThree;
import CoinforgedPackage.cards.tempCards.BlueTwo;
import CoinforgedPackage.powers.SnakeEyesPower;

public class RollBlueAction extends RollBaseAction {

    public RollBlueAction(int magic) {
        super(magic);
    }

    public RollBlueAction(int magic, int minroll, int maxroll) {
        super(magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int block;
        if (player.hasPower(SnakeEyesPower.POWER_ID)) {
            block = 1;
        } else {
            block = AbstractDungeon.cardRandomRng.random(minroll, maxroll);
        }

        AbstractCard cardToAdd = null;
        switch (block) {
            case 1:
                cardToAdd = new BlueOne();
                break;
            case 2:
                cardToAdd = new BlueTwo();
                break;
            case 3:
                cardToAdd = new BlueThree();
                break;
            case 4:
                cardToAdd = new BlueFour();
                break;
            case 5:
                cardToAdd = new BlueFive();
                break;
            case 6:
                cardToAdd = new BlueSix();
                break;
            default:
                cardToAdd = new BlueSix(block);
                break;
        }
        return cardToAdd;
    }
}
