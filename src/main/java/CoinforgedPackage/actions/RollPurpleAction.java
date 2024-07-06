package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.tempCards.PurpleFive;
import CoinforgedPackage.cards.tempCards.PurpleFour;
import CoinforgedPackage.cards.tempCards.PurpleOne;
import CoinforgedPackage.cards.tempCards.PurpleSix;
import CoinforgedPackage.cards.tempCards.PurpleThree;
import CoinforgedPackage.cards.tempCards.PurpleTwo;
import CoinforgedPackage.powers.SnakeEyesPower;

public class RollPurpleAction extends RollBaseAction {

    public RollPurpleAction(int magic) {
        super(magic);
    }

    public RollPurpleAction(int magic, int minroll, int maxroll) {
        super(magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int vigor;
        if (player.hasPower(SnakeEyesPower.POWER_ID)) {
            vigor = 1;
        } else {
            vigor = AbstractDungeon.cardRandomRng.random(minroll, maxroll);
        }

        AbstractCard cardToAdd = null;
        switch (vigor) {
            case 1:
                cardToAdd = new PurpleOne();
                break;
            case 2:
                cardToAdd = new PurpleTwo();
                break;
            case 3:
                cardToAdd = new PurpleThree();
                break;
            case 4:
                cardToAdd = new PurpleFour();
                break;
            case 5:
                cardToAdd = new PurpleFive();
                break;
            case 6:
                cardToAdd = new PurpleSix();
                break;
            default:
                cardToAdd = new PurpleSix(vigor);
                break;
        }
        return cardToAdd;
    }
}
