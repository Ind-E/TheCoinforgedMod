package CoinforgedPackage.old;

import static CoinforgedPackage.old.oldUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;

import CoinforgedPackage.util.Wiz;

public class BuyInAction extends AbstractGameAction {

    public BuyInAction() {
        super();
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        Wiz.atb(new GainEnergyAction(getNumChips()));
        this.isDone = true;
    }
}