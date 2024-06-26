package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

public class GamblePower extends BasePower {
    public static final String POWER_ID = makeID(GamblePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public GamblePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.amount = amount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("GamblePower")));
        Wiz.atb(new ApplyPowerAction(this.owner, this.owner, new GoldIfWinPower(this.owner, this.amount)));
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

}
