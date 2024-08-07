package CoinforgedPackage.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

public class CripplingDebtPower extends BasePower implements HealthBarRenderPower {
    public static final String POWER_ID = makeID(CripplingDebtPower.class.getSimpleName());

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public CripplingDebtPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfRound() {
        flashWithoutSound();
        this.stackPower((int) Math.max(1, Math.round(this.amount * 0.25)));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
        checkInstantKill();
    }

    @Override
    public void onInitialApplication() {
        checkInstantKill();
    }

    public void checkInstantKill() {
        if (this.owner.currentHealth > 0 && this.owner.currentHealth <= this.amount) {
            Wiz.atb(new InstantKillAction(this.owner));
            Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.owner, CripplingDebtPower.POWER_ID));
        }
    }

    @Override
    public Color getColor() {
        return new Color(53f / 255f, 65f / 255f, 100f / 255f, 1f);
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }
}
