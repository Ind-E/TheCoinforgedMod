package CoinforgedPackage.old;


import static CoinforgedPackage.old.oldUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.old.chips.BlackChip;
import CoinforgedPackage.old.chips.CrackedChip;
import CoinforgedPackage.old.chips.GrayChip;
import CoinforgedPackage.old.chips.OrangeChip;
import CoinforgedPackage.powers.ModifiedHandSizePower;

public class SpendChipsAction extends AbstractGameAction {
    private int chips;
    private boolean exhaust = false;

    public SpendChipsAction(int chips) {
        super();
        this.chips = chips;
    }

    public SpendChipsAction(int chips, boolean exhaust) {
        this(chips);
        this.exhaust = exhaust;
    }

    public void update() {
        int chipsSpent = 0;
        if (getNumChips() >= chips) {

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof BlackChip) {
                    addToTop(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                    chipsSpent += BlackChip.BlackChipValue;
                }
                if (chipsSpent >= chips) {
                    this.isDone = true;
                    return;
                }
            }

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.POKER_CHIP)) {
                    if (c instanceof GrayChip) {
                        addToTop(new GainEnergyAction(1));
                    } else if (c instanceof OrangeChip) {
                        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ModifiedHandSizePower(1)));
                    }
                    if (exhaust || c instanceof CrackedChip) {
                        addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                    } else {
                        addToTop(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                    }
                    if (!(c instanceof BlackChip)) {
                        chipsSpent++;
                    }
                    
                    if (chipsSpent >= chips) {
                        break;
                    }
                }
            }
        }
        this.isDone = true;
    }
}