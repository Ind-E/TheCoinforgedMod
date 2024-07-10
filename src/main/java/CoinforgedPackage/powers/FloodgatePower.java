package CoinforgedPackage.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class FloodgatePower extends BasePower implements OnReceivePowerPower, OnCreateCardInterface {
    public static final String POWER_ID = makeID(FloodgatePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
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
            Wiz.atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount, true),
                    DamageInfo.DamageType.THORNS, AttackEffect.NONE));
        }
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof ModifiedHandSizePower) {
            System.out.println("power instance of ModifiedHandSizePower");
            int cardsInHand = p.hand.size();
            AbstractPower handSizePower = p.getPower(ModifiedHandSizePower.POWER_ID);
            int handSizeReduction = handSizePower != null ? handSizePower.amount : 0;
            if (p.hasPower(ModifiedHandSizePower.POWER_ID)) {
                if (cardsInHand >= power.amount + BaseMod.DEFAULT_MAX_HAND_SIZE + handSizeReduction &&
                        cardsInHand < handSizeReduction + BaseMod.DEFAULT_MAX_HAND_SIZE) {
                    damageAll();
                }
            } else if (cardsInHand >= power.amount + BaseMod.DEFAULT_MAX_HAND_SIZE) {
                damageAll();
            }
        }
        return true;
    }

    @Override
    public void onCreateCard(AbstractCard card) {
        if (BaseMod.MAX_HAND_SIZE == AbstractDungeon.player.hand.size()) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.uuid == card.uuid) {
                    damageAll();
                }
            }
        }
    }

    private void damageAll() {
        flash();
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        Wiz.atb(new VFXAction(p, new CleaveEffect(), 0.1F));
        Wiz.atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount, true),
                DamageInfo.DamageType.THORNS, AttackEffect.NONE));
    }

}
