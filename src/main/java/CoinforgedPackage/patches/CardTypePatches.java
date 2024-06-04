package CoinforgedPackage.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import CoinforgedPackage.cards.CustomTags;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

import static CoinforgedPackage.CoinforgedMain.modID;

public class CardTypePatches {

    // Taken from Laugic who took it from NellyDevo
    // Thank you!

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
    public static class SingleCardViewPopupRenderCardTypeTextPatch {
        private static UIStrings uiStrings;
        private static String[] TEXT;

        @SpireInsertPatch(localvars = { "label" }, locator = Locator.class)
        public static void Insert(SingleCardViewPopup __instance, SpriteBatch sb, @ByRef String[] label) {
            if (uiStrings == null) {
                String ID = modID + ":CardTypeStrings";
                uiStrings = CardCrawlGame.languagePack.getUIString(ID);
                TEXT = uiStrings.TEXT;
            }
            AbstractCard reflectedCard = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            boolean isChip = reflectedCard.hasTag(CustomTags.POKER_CHIP);
            if (isChip)
                label[0] = TEXT[0];
        }

        public static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderType")
    public static class AbstractCardRenderTypePatch {
        private static UIStrings uiStrings;
        private static String[] TEXT;

        @SpireInsertPatch(localvars = { "text" }, locator = Locator.class)
        public static void Insert(AbstractCard __instance, SpriteBatch sb, @ByRef String[] text) {
            if (uiStrings == null) {
                String ID = modID + ":CardTypeStrings";
                uiStrings = CardCrawlGame.languagePack.getUIString(ID);
                TEXT = uiStrings.TEXT;
            }
            boolean isChip = false;
            if (__instance.hasTag(CustomTags.POKER_CHIP))
                isChip = true;
            if (isChip)
                text[0] = TEXT[0];
        }

        public static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderRotatedText");

                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }
}
