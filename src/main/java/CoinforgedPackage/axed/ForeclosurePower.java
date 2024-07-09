package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

public class ForeclosurePower extends BasePower {
    public static final String POWER_ID = makeID(ForeclosurePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ForeclosurePower() {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.adp(), -1);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer)
            return;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            for (AbstractPower p : mo.powers) {
                if (p.type == PowerType.DEBUFF) {
                    Wiz.applyToEnemy(mo, p);
                }
            }
        }
    }

}