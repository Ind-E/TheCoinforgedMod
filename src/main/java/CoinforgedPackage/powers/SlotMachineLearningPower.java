package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class SlotMachineLearningPower extends BasePower {
    public static final String POWER_ID = makeID(SlotMachineLearningPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int preDrawHandSize = 0;

    public SlotMachineLearningPower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
        this.amount = amount;
        AbstractDungeon.player.gameHandSize += amount;
    }

    public void onRemove() {
        AbstractDungeon.player.gameHandSize -= this.amount;
    }

    @Override
    public void updateDescription() {
        if (amount != 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3] + amount * 3
                    + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[3] + amount * 3
                    + DESCRIPTIONS[4];
        }

    }

    @Override
    public void atStartOfTurn() {
        preDrawHandSize = AbstractDungeon.player.hand.size();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (preDrawHandSize + AbstractDungeon.player.gameHandSize > BaseMod.MAX_HAND_SIZE) {
            Wiz.applyToSelf(new StrengthPower(AbstractDungeon.player, amount * 3));
        }
    }
}
