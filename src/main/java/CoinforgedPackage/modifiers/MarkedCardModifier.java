package CoinforgedPackage.modifiers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.CoinforgedMod;
import basemod.abstracts.AbstractCardModifier;

public class MarkedCardModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!card.hasTag(CoinforgedMod.MARKED)) {
            return rawDescription;
        }
        return CoinforgedMod.modID + ":Marked. NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MarkedCardModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(CoinforgedMod.MARKED)) {
            card.tags.add(CoinforgedMod.MARKED);
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new GainEnergyAction(1));
        addToBot(new DrawCardAction(1));
    }

    public Color getGlow(AbstractCard card) {
        return Color.MAGENTA.cpy();
    }

}