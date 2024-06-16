package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField.ExhaustiveFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.actions.RollGreenAction;
import CoinforgedPackage.actions.RollPurpleAction;
import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class DiceBag extends AbstractCoinforgedCard {
    private static final int EXHAUSTIVE = 4;
    private static final int UPG_EXHAUSTIVE = 2;

    public static final String ID = makeID(DiceBag.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0);

    public DiceBag() {
        super(ID, info, true);
        ExhaustiveFields.baseExhaustive.set(this, EXHAUSTIVE);
        ExhaustiveFields.exhaustive.set(this, EXHAUSTIVE);
        returnToHand = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            ExhaustiveFields.baseExhaustive.set(this, ExhaustiveFields.baseExhaustive.get(this) + UPG_EXHAUSTIVE);
            ExhaustiveFields.exhaustive.set(this, ExhaustiveFields.exhaustive.get(this) + UPG_EXHAUSTIVE);
            ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int rand = AbstractDungeon.cardRandomRng.random(3);

        switch (rand) {
            case 0:
                Wiz.atb(new RollBlueAction(1));
                break;
            case 1:
                Wiz.atb(new RollRedAction(1));
                break;
            case 2:
                Wiz.atb(new RollGreenAction(1));
                break;
            case 3:
                Wiz.atb(new RollPurpleAction(1));
                break;
            default:
                break;
        }
    }

    @Override
    public ArrayList<CardTags> getPreviewTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        tags.add(CustomTags.BLUE_DIE);
        tags.add(CustomTags.GREEN_DIE);
        tags.add(CustomTags.PURPLE_DIE);
        return tags;
    }
}
