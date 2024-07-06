package CoinforgedPackage.relics;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import CoinforgedPackage.character.Coinforged;

public class CasinoCatalyst extends BaseRelic {
    private static final String NAME = "CasinoCatalyst";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.MAGICAL;

    public CasinoCatalyst() {
        super(ID, NAME, Coinforged.Enums.CARD_COLOR, TIER, sfx);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (!grayscale) {
            ++counter;
        }

        if (counter == 2) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(1));
            counter = -1;
            grayscale = true;
        }

    }

    @Override
    public void onVictory() {
        counter = -1;
        grayscale = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CasinoCatalyst();
    }

}
