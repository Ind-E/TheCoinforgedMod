package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollGreenAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.LoadedDicePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;

public class PocketPair extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 2;

    public static final String ID = makeID(PocketPair.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public PocketPair() {
        super(ID, info, true);
        this.exhaust = true;
        setMagic(DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        RollGreenAction green = new RollGreenAction(1);
        AbstractCard c1 = green.roll(), c2 = green.roll();
        if (p.hasPower(LoadedDice.ID)) {
            int amt = p.getPower(LoadedDicePower.POWER_ID).amount;
            c1.baseMagicNumber += amt;
            c2.baseMagicNumber += amt;
            c1.magicNumber = c1.baseMagicNumber;
            c2.magicNumber = c2.baseMagicNumber;
            c1.initializeDescription();
            c2.initializeDescription();
        }
        Wiz.atb(new MakeTempCardInHandAction(c1, true, true));
        Wiz.atb(new MakeTempCardInHandAction(c2, true, true));
        if (upgraded) {
            CardModifierManager.addModifier(c1, new RetainMod());
            CardModifierManager.addModifier(c2, new RetainMod());
        }
    }

    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.GREEN_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new PocketPair();
    }
}