package CoinforgedPackage.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.tempCards.RedFive;
import CoinforgedPackage.cards.tempCards.RedFour;
import CoinforgedPackage.cards.tempCards.RedOne;
import CoinforgedPackage.cards.tempCards.RedSix;
import CoinforgedPackage.cards.tempCards.RedThree;
import CoinforgedPackage.cards.tempCards.RedTwo;
import CoinforgedPackage.powers.AdvantagePower;
import CoinforgedPackage.powers.RedKingPower;
import CoinforgedPackage.powers.SnakeEyesPower;

public class RollRedAction extends RollBaseAction {
    private boolean redKingActive = false;

    public RollRedAction(int magic) {
        super(magic);
    }

    public RollRedAction(int magic, int minroll, int maxroll) {
        super(magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int dmg;
        if (player.hasPower(SnakeEyesPower.POWER_ID)) {
            dmg = 1;
        } else if (player.hasPower(AdvantagePower.POWER_ID)) {
            int adv = player.getPower(AdvantagePower.POWER_ID).amount;
            List<Integer> rolls = new ArrayList<Integer>();
            for (int i = 0; i < adv; i++) {
                ;
                rolls.add(AbstractDungeon.cardRandomRng.random(minroll, maxroll));
            }
            dmg = Collections.max(rolls);

        } else {
            dmg = AbstractDungeon.cardRandomRng.random(minroll, maxroll);
        }

        if (player.hasPower(RedKingPower.POWER_ID)) {
            redKingActive = true;
        }

        AbstractCard cardToAdd = null;
        switch (dmg) {
            case 1:
                cardToAdd = redKingActive ? new RedOne(true) : new RedOne();
                break;
            case 2:
                cardToAdd = redKingActive ? new RedTwo(true) : new RedTwo();
                break;
            case 3:
                cardToAdd = redKingActive ? new RedThree(true) : new RedThree();
                break;
            case 4:
                cardToAdd = redKingActive ? new RedFour(true) : new RedFour();
                break;
            case 5:
                cardToAdd = redKingActive ? new RedFive(true) : new RedFive();
                break;
            case 6:
                cardToAdd = redKingActive ? new RedSix(true) : new RedSix();
            default:
                cardToAdd = redKingActive ? new RedSix(dmg, true) : new RedSix(dmg);
                break;
        }
        return cardToAdd;
    }
}
