package CoinforgedPackage.cards;

import static CoinforgedPackage.CoinforgedMain.imagePath;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import CoinforgedPackage.modifiers.ReturnModifier;
import CoinforgedPackage.util.CardStats;
import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;

public abstract class AbstractReturnCard extends AbstractCoinforgedCard {
    protected List<TooltipInfo> tooltip;

    public AbstractReturnCard(String ID, CardStats info) {
        this(ID, info, false);
    }

    public AbstractReturnCard(String ID, CardStats info, boolean isMultiPreview) {
        super(ID, info, isMultiPreview);
        CardModifierManager.addModifier(this, new ReturnModifier());
        setOrbTexture(imagePath("character/cardback/return_orb.png"), imagePath("character/cardback/return_orb_p.png"));
        tags.add(CustomTags.RETURN);
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        if (tooltip == null) {
            tooltip = new ArrayList<>();
            tooltip.add(new TooltipInfo(
                    BaseMod.getKeywordProper(makeID("return")),
                    BaseMod.getKeywordDescription(makeID("return"))));
        }
        return tooltip;
    }

    @Override
    public boolean hasEnoughEnergy() {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            cantUseMessage = TEXT[9];
            return false;
        }
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (!p.canPlayCard(this)) {
                cantUseMessage = TEXT[13];
                return false;
            }
        }
        if (AbstractDungeon.player.hasPower(EntanglePower.POWER_ID) && type == CardType.ATTACK) {
            cantUseMessage = TEXT[10];
            return false;
        }
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (!r.canPlay(this))
                return false;
        }
        for (AbstractBlight b : AbstractDungeon.player.blights) {
            if (!b.canPlay(this))
                return false;
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.canPlay(this))
                return false;
        }
        if (AbstractDungeon.player.discardPile.size() >= costForTurn || freeToPlay() || isInAutoplay)
            return true;
        cantUseMessage = TEXT[11];
        return false;
    }
}
