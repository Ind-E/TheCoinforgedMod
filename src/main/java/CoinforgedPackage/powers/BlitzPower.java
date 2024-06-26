package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

public class BlitzPower extends BasePower {
    public static final String POWER_ID = makeID(BlitzPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public BlitzPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.owner, BlitzPower.POWER_ID));
        }

    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        Wiz.atb(new GainEnergyAction(1));
    }
}