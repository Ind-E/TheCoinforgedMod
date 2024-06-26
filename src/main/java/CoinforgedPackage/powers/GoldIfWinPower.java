package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import CoinforgedPackage.util.Wiz;

public class GoldIfWinPower extends BasePower {
    public static final String POWER_ID = makeID(GoldIfWinPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public GoldIfWinPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.owner, GoldIfWinPower.POWER_ID));
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.amount > 0) {
            p.gainGold(this.amount);
            AbstractDungeon.effectList.add(new RainingGoldEffect(this.amount * 2, true));
        }

    }

}
