package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import CoinforgedPackage.util.Wiz;

public class PlayerAppliedDrawReductionPower extends DrawReductionPower {
    public static final String POWER_ID = makeID(PlayerAppliedDrawReductionPower.class.getSimpleName());

    public PlayerAppliedDrawReductionPower(AbstractCreature owner, int amount) {
        super(owner, amount);
    }

    @Override
    public void atEndOfRound() {
        Wiz.atb(new ReducePowerAction(this.owner, this.owner, "Draw Reduction", 1));
    }
}
