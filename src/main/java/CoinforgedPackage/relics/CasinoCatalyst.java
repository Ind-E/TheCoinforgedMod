package CoinforgedPackage.relics;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import CoinforgedPackage.actions.CasinoCatalystAction;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.character.Coinforged;

public class CasinoCatalyst extends BaseRelic {
    private static final String NAME = "CasinoCatalyst";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.HEAVY;
    private boolean markedCardPlayedThisCombat = false;

    public CasinoCatalyst() {
        super(ID, NAME, Coinforged.Enums.CARD_COLOR, TIER, sfx);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atPreBattle() {
        flash();
        if (!this.pulse) {
            beginPulse();
            this.pulse = true;
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        if (GameActionManager.turn == 1) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new CasinoCatalystAction());
            this.markedCardPlayedThisCombat = false;
        }

    }

    @Override
    public void onVictory() {
        this.pulse = false;
        if (!markedCardPlayedThisCombat && AbstractDungeon.player.hasRelic(LuckRelic.ID)) {
            LuckRelic luckRelic = (LuckRelic) AbstractDungeon.player.getRelic(LuckRelic.ID);
            if (luckRelic != null) {
                luckRelic.flash();
                luckRelic.setCounter(luckRelic.getCounter() + 1);
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (markedCardPlayedThisCombat == false && c.hasTag(CustomTags.MARKED)) {
            markedCardPlayedThisCombat = true;
            this.pulse = false;
            stopPulse();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CasinoCatalyst();
    }

}
