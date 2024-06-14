package CoinforgedPackage.cards;

import static CoinforgedPackage.CoinforgedMain.imagePath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import CoinforgedPackage.modifiers.ReturnModifier;
import CoinforgedPackage.util.CardStats;
import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;

public abstract class AbstractReturnCard extends AbstractCoinforgedCard {
    protected ArrayList<TooltipInfo> tooltip;

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
            this.cantUseMessage = TEXT[9];
            return false;
        } else {
            @SuppressWarnings("rawtypes")
            Iterator var1 = AbstractDungeon.player.powers.iterator();

            AbstractPower p;
            do {
                if (!var1.hasNext()) {
                    if (AbstractDungeon.player.hasPower("Entangled")
                            && this.type == com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK) {
                        this.cantUseMessage = TEXT[10];
                        return false;
                    }

                    var1 = AbstractDungeon.player.relics.iterator();

                    AbstractRelic r;
                    do {
                        if (!var1.hasNext()) {
                            var1 = AbstractDungeon.player.blights.iterator();

                            AbstractBlight b;
                            do {
                                if (!var1.hasNext()) {
                                    var1 = AbstractDungeon.player.hand.group.iterator();

                                    AbstractCard c;
                                    do {
                                        if (!var1.hasNext()) { // this part was changed
                                            if (AbstractDungeon.player.discardPile.size() < this.costForTurn
                                                    && !this.isInAutoplay) {
                                                this.cantUseMessage = "Not enough cards in #rDiscard #rPile.";
                                                return false;
                                            }

                                            return true;
                                        }

                                        c = (AbstractCard) var1.next();
                                    } while (c.canPlay(this));

                                    return false;
                                }

                                b = (AbstractBlight) var1.next();
                            } while (b.canPlay(this));

                            return false;
                        }

                        r = (AbstractRelic) var1.next();
                    } while (r.canPlay(this));

                    return false;
                }

                p = (AbstractPower) var1.next();
            } while (p.canPlayCard(this));

            this.cantUseMessage = TEXT[13];
            return false;
        }
    }

}
