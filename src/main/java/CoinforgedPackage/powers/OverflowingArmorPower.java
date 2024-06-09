package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class OverflowingArmorPower extends BasePower {
    public static final String POWER_ID = makeID(OverflowingArmorPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public OverflowingArmorPower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onPlayCard(AbstractCard c) {
        if (AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE - 2) {
            Wiz.atb(new GainBlockAction(AbstractDungeon.player, amount));
        }
    }

}
