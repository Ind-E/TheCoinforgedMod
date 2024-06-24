package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import CoinforgedPackage.util.Wiz;

public class PincerAttackAction extends AbstractGameAction {
    private int damage;
    private AbstractMonster target;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public PincerAttackAction(int damage, AbstractMonster target) {
        super();
        this.damage = damage;
        this.target = target;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (Wiz.adp().hand.isEmpty()) {
            isDone = true;
        } else if (Wiz.adp().hand.size() == 1) {
            if (Wiz.adp().hand.getBottomCard().costForTurn == -1) {
                dealDamage(EnergyPanel.getCurrentEnergy());
            } else if (Wiz.adp().hand.getBottomCard().costForTurn > 0) {
                dealDamage(Wiz.adp().hand.getBottomCard().costForTurn);
            }

            Wiz.adp().hand.moveToDiscardPile(Wiz.adp().hand.getBottomCard());
            isDone = true;
        } else {
            if (Wiz.adp().hand.getTopCard().costForTurn == -1) {
                dealDamage(EnergyPanel.getCurrentEnergy());
            } else if (Wiz.adp().hand.getTopCard().costForTurn > 0) {
                dealDamage(Wiz.adp().hand.getTopCard().costForTurn);
            }

            if (Wiz.adp().hand.getBottomCard().costForTurn == -1) {
                dealDamage(EnergyPanel.getCurrentEnergy());
            } else if (Wiz.adp().hand.getBottomCard().costForTurn > 0) {
                dealDamage(Wiz.adp().hand.getBottomCard().costForTurn);
            }

            Wiz.adp().hand.moveToDiscardPile(Wiz.adp().hand.getTopCard());
            Wiz.adp().hand.moveToDiscardPile(Wiz.adp().hand.getBottomCard());
            isDone = true;
        }
    }

    private void dealDamage(int x) {
        for (int i = 0; i < x; i++) {
            Wiz.atb(new DamageAction(target, new DamageInfo(Wiz.adp(), damage, DamageInfo.DamageType.NORMAL),
                    AttackEffect.SLASH_VERTICAL));
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
    }
}
