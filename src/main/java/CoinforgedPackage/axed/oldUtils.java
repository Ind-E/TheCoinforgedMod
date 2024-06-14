package CoinforgedPackage.axed;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.axed.chips.BlackChip;
import CoinforgedPackage.cards.CustomTags;

public class oldUtils {
    public static int getNumChips() {
        int chips = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof BlackChip) {
                chips += BlackChip.BlackChipValue;
                continue;
            }
            if (c.hasTag(CustomTags.POKER_CHIP)) {
                chips++;
            }
        }
        return chips;
    }
}
