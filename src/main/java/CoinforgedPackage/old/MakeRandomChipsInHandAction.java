package CoinforgedPackage.old;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.old.chips.BasicChip;
import CoinforgedPackage.old.chips.BlackChip;
import CoinforgedPackage.old.chips.BlueChip;
import CoinforgedPackage.old.chips.CrackedChip;
import CoinforgedPackage.old.chips.GrayChip;
import CoinforgedPackage.old.chips.GreenChip;
import CoinforgedPackage.old.chips.OrangeChip;
import CoinforgedPackage.old.chips.RedChip;

public class MakeRandomChipsInHandAction extends AbstractGameAction {

    public MakeRandomChipsInHandAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        AbstractCard chip;
        for (int i = 0; i < amount; i++) {
            chip = getRandomChip();

            addToTop(new MakeTempCardInHandAction(chip));
        }

        isDone = true;
    }

    private static AbstractCard getRandomChip() {
        int roll = AbstractDungeon.cardRandomRng.random(7);
        switch (roll) {
            case 0:
                return new BlueChip();
            case 1:
                return new BlackChip();
            case 2:
                return new CrackedChip();
            case 3:
                return new GrayChip();
            case 4:
                return new GreenChip();
            case 5:
                return new OrangeChip();
            case 6:
                return new RedChip();
            case 7:
                return new BasicChip();
            default:
                return null;
        }
    }

}
