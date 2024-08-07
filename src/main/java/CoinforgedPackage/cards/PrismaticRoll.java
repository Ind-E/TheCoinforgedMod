package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.actions.RollGreenAction;
import CoinforgedPackage.actions.RollPurpleAction;
import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class PrismaticRoll extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 1;

    public static final String ID = makeID(PrismaticRoll.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2);

    public PrismaticRoll() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL);
        setExhaust(true, false);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollRedAction(magicNumber));
        Wiz.atb(new RollBlueAction(magicNumber));
        Wiz.atb(new RollGreenAction(magicNumber));
        Wiz.atb(new RollPurpleAction(magicNumber));
    }

    @Override
    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        tags.add(CustomTags.BLUE_DIE);
        tags.add(CustomTags.GREEN_DIE);
        tags.add(CustomTags.PURPLE_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrismaticRoll();
    }
}