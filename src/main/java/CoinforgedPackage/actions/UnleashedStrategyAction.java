package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.util.Wiz;

public class UnleashedStrategyAction extends AbstractGameAction {
    public UnleashedStrategyAction() {
        super();
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        Wiz.atb(new GainEnergyAction(p.hand.size() / 3));
        this.isDone = true;
    }
}