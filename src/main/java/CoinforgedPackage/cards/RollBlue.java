package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class RollBlue extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 3;
    private static final int UPG_DICE_TO_ROLL = 1;

    public static final String ID = makeID(RollBlue.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public RollBlue() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollBlueAction(magicNumber));
    }

    public ArrayList<CardTags> getPreviewTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.BLUE_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RollBlue();
    }
}
