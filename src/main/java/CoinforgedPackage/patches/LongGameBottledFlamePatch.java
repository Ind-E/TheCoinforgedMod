package CoinforgedPackage.patches;

import java.util.ArrayList;
import java.util.List;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import CoinforgedPackage.cards.LongGame;

@SpirePatch(clz = CardGroup.class, method = "getPurgeableCards")
public class LongGameBottledFlamePatch {

    @SpirePostfixPatch()
    public static CardGroup PostFix(CardGroup __retVal, CardGroup __instance) {
        List<AbstractCard> cardsToRemove = new ArrayList<>();

        for (AbstractCard c : __retVal.group) {
            if (c.cardID.equals(LongGame.ID)) {
                cardsToRemove.add(c);
            }
        }
        __retVal.group.removeAll(cardsToRemove);

        return __retVal;
    }
}
