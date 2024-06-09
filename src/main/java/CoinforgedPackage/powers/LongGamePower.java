package CoinforgedPackage.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

import com.megacrit.cardcrawl.helpers.FontHelper;

public class LongGamePower extends BasePower implements NonStackablePower {
    public static final String POWER_ID = makeID(LongGamePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private AbstractCard card;

    public LongGamePower(AbstractCreature owner, int amount, AbstractCard card) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount, false);
        this.card = card;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount;
        if (this.amount == 1) {
            this.description += DESCRIPTIONS[1];
        } else {
            this.description += DESCRIPTIONS[2];
        }
        this.description += FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[3];
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount == 1) {
            Wiz.atb(new MakeTempCardInHandAction(this.card));
            Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.source, this.ID));
        } else {
            Wiz.atb(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }
}