package CoinforgedPackage.patches;

import java.util.ArrayList;
import java.util.List;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;

import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

public class NoCompendiumColorlessPatch {
    @SpirePatch(clz = CardLibraryScreen.class, method = "initialize")
    public static class Initialize {

        @SpirePostfixPatch()
        public static void Postfix(CardLibraryScreen __instance) {
            CardGroup colorlessCards = (CardGroup) ReflectionHacks.getPrivate(__instance, CardLibraryScreen.class,
                    "colorlessCards");

            List<AbstractCard> cardsToRemove = new ArrayList<>();
            for (AbstractCard card : colorlessCards.group) {
                if (card.getClass().isAnnotationPresent(NoCompendium.class)) {
                    cardsToRemove.add(card);
                }
            }

            colorlessCards.group.removeAll(cardsToRemove);
        }
    }
}