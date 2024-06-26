package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

public class MelonPower extends BasePower {
    public static final String POWER_ID = makeID(MelonPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MelonPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageType.THORNS && info.type != DamageType.HP_LOSS
                && info.owner != this.owner) {
            this.flash();
            addToTop(new DrawCardAction(1));
        }
        return damageAmount;

    }

    @Override
    public void atStartOfTurn() {
        Wiz.atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, MelonPower.POWER_ID));
    }
}