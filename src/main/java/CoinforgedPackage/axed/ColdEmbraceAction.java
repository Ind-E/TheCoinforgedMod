package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.modifiers.buffBlockModifier;
import basemod.helpers.CardModifierManager;

public class ColdEmbraceAction extends AbstractGameAction {
    private int blockBuff;

    public ColdEmbraceAction(int blockBuff) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.blockBuff = blockBuff;
    }

    @Override
    public void update() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            CardModifierManager.addModifier(card, new buffBlockModifier(blockBuff));
        }
        isDone = true;
    }
}
