package CoinforgedPackage.axed;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.axed.chips.BasicChip;
import CoinforgedPackage.axed.chips.BlackChip;
import CoinforgedPackage.axed.chips.BlueChip;
import CoinforgedPackage.axed.chips.CrackedChip;
import CoinforgedPackage.axed.chips.GrayChip;
import CoinforgedPackage.axed.chips.GreenChip;
import CoinforgedPackage.axed.chips.OrangeChip;
import CoinforgedPackage.axed.chips.RedChip;

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
