package CoinforgedPackage.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class MinHandSizePower extends BasePower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = makeID(MinHandSizePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MinHandSizePower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.adp(), amount);
        if (BaseMod.MAX_HAND_SIZE < amount) {
            Wiz.applyToSelf(new ModifiedHandSizePower(6 - BaseMod.MAX_HAND_SIZE));
        }
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof ModifiedHandSizePower && power.amount < 0 && BaseMod.MAX_HAND_SIZE == amount) {
            flash();
            return false;
        }
        return true;
    }

    @Override
    public void stackPower(int stackAmount) {
        return;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source,
            int stackAmount) {
        if (!(power instanceof ModifiedHandSizePower)) {
            return stackAmount;
        }
        if (BaseMod.MAX_HAND_SIZE + power.amount < amount) {
            flash();
            return -(BaseMod.MAX_HAND_SIZE - amount);
        } else {
            return power.amount;
        }
    }

}
