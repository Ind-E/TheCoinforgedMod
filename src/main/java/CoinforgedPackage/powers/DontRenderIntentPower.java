package CoinforgedPackage.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BeforeRenderIntentPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DontRenderIntentPower extends BasePower implements BeforeRenderIntentPower, NonStackablePower {
    public static final String POWER_ID = makeID(DontRenderIntentPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public DontRenderIntentPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1];
        }
    }

    @Override
    public void atEndOfRound() {
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        } else {
            addToTop(new ReducePowerAction(owner, owner, this, 1));
        }

    }

    @Override
    public boolean beforeRenderIntent(AbstractMonster arg0) {
        return amount != 1;
    }
}