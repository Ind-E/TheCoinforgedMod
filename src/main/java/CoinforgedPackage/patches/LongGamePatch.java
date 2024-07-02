package CoinforgedPackage.patches;

import java.util.ArrayList;
import java.util.List;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.LongGame;
import CoinforgedPackage.powers.LongGamePower;
import CoinforgedPackage.util.Wiz;

@SpirePatch(clz = CardGroup.class, method = "initializeDeck")
public class LongGamePatch {
    @SpirePostfixPatch()
    public static void PostFix(CardGroup __instance) {
        AbstractPlayer p = AbstractDungeon.player;

        List<AbstractCard> cardsToRemove = new ArrayList<>();

        for (AbstractCard c : __instance.group) {
            if (c instanceof LongGame) {
                Wiz.applyToSelf(new LongGamePower(p, c.magicNumber, c.makeStatEquivalentCopy()));
                cardsToRemove.add(c);
            }
        }

        __instance.group.removeAll(cardsToRemove);
    }
}
