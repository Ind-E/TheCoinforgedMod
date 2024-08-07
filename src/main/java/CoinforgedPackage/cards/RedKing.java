package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.modifiers.RedKingModifier;
import CoinforgedPackage.powers.RedKingPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.helpers.CardModifierManager;

public class RedKing extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 2;

    public static final String ID = makeID(RedKing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public RedKing() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(RedKing.ID))
            Wiz.atb(new ApplyPowerAction(p, p, new RedKingPower(p)));
        Wiz.atb(new RollRedAction(magicNumber));
        for (AbstractCard card : p.hand.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }

        for (AbstractCard card : p.drawPile.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }

        for (AbstractCard card : p.discardPile.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }

        for (AbstractCard card : p.exhaustPile.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }
    }

    @Override
    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RedKing();
    }
}
