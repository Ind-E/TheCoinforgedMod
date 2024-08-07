package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.Wiz;

public class CardCascadePower extends BasePower {
    public static final String POWER_ID = makeID(CardCascadePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CardCascadePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        flash();
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(owner, amount, DamageType.THORNS),
                AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void atStartOfTurn() {
        Wiz.att(new RemoveSpecificPowerAction(owner, owner, CardCascadePower.POWER_ID));
    }
}