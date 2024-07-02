package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class SnakeEyesAction extends AbstractGameAction {

    public void update() {
        for (int i = 0; i < BaseMod.MAX_HAND_SIZE - Wiz.adp().hand.size(); i++) {
            switch (AbstractDungeon.cardRandomRng.random(0, 3)) {
                case 0:
                    Wiz.atb(new RollBlueAction(1, 1, 1));
                    break;
                case 1:
                    Wiz.atb(new RollRedAction(1, 1, 1));
                    break;
                case 2:
                    Wiz.atb(new RollGreenAction(1, 1, 1));
                    break;
                case 3:
                    Wiz.atb(new RollPurpleAction(1, 1, 1));
                    break;
                default:
                    Wiz.atb(new RollPurpleAction(1, 2, 2));
                    break;
            }
        }
        isDone = true;
    }
}