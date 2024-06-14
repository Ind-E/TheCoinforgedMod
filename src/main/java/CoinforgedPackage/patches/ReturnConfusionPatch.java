package CoinforgedPackage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ConfusionPower;

import CoinforgedPackage.cards.CustomTags;

@SpirePatch(clz = ConfusionPower.class, method = "onCardDraw")
public class ReturnConfusionPatch {
    @SpirePostfixPatch
    public static SpireReturn<Void> Prefix(ConfusionPower __instance, AbstractCard ___card) {
        if (___card.hasTag(CustomTags.RETURN)) {
            if (___card.cost >= 0) {
                int newCost = AbstractDungeon.cardRandomRng.random(7);
                if (___card.cost != newCost) {
                    ___card.cost = newCost;
                    ___card.costForTurn = ___card.cost;
                    ___card.isCostModified = true;
                }
            }

            ___card.freeToPlayOnce = false;
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}