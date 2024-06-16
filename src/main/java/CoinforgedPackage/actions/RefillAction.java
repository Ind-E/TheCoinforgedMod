package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import CoinforgedPackage.util.Wiz;

public class RefillAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private int energyOnUse = -1;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean upgraded;

    public RefillAction(boolean freeToPlayOnce, int energyOnUse, boolean upgraded) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;
        if (p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            p.getRelic(ChemicalX.ID).flash();
        }
        if (upgraded)
            effect++;
        if (effect > 0) {
            Wiz.atb(new DrawCardAction(effect));
            if (!freeToPlayOnce)
                p.energy.use(EnergyPanel.totalCount);
        }
        isDone = true;
    }
}
