package CoinforgedPackage.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class FloodgatePower extends BasePower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = makeID(FloodgatePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private AbstractPlayer p = AbstractDungeon.player;

    public FloodgatePower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        if (AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE) {
            Wiz.atb(new VFXAction(p, new CleaveEffect(), 0.1F));
            Wiz.atb(new DamageAllEnemiesAction(p, amount, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
        }
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof ModifiedHandSizePower && BaseMod.MAX_HAND_SIZE <= power.amount
                + BaseMod.DEFAULT_MAX_HAND_SIZE + AbstractDungeon.player.getPower(ModifiedHandSizePower.POWER_ID).amount) {
            // if hand becomes full by reducing hand size
            Wiz.atb(new VFXAction(p, new CleaveEffect(), 0.1F));
            Wiz.atb(new DamageAllEnemiesAction(p, amount, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
        }
        return true;
    }

}
