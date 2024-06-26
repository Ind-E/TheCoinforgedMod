package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import CoinforgedPackage.util.Wiz;

public class CycleStrikeAction extends AbstractGameAction {
    public int[] multiDamage;

    private boolean freeToPlayOnce = false;
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public CycleStrikeAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType,
            boolean freeToPlayOnce, int energyOnUse) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            this.p.getRelic(ChemicalX.ID).flash();
        }
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                if (i == 0) {
                    Wiz.atb(new SFXAction("ATTACK_WHIRLWIND"));
                    Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.0F));
                }
                Wiz.atb(new SFXAction("ATTACK_HEAVY"));
                Wiz.atb(new VFXAction(this.p, new CleaveEffect(), 0.0F));
                Wiz.atb(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType,
                        AbstractGameAction.AttackEffect.NONE, true));
            }

            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
            Wiz.atb(new DrawCardAction(effect));
            Wiz.atb(new DiscardAction(p, p, effect, false));
        }
        Wiz.atb(new GainEnergyAction(1));
        this.isDone = true;
    }
}
