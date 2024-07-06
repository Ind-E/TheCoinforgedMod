package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class BlueDrink extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 1;

    public static final String ID = makeID(BlueDrink.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);

    public BlueDrink() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollBlueAction(magicNumber));
        Wiz.atb(new ArmamentsAction(true));
    }

    @Override
    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.BLUE_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlueDrink();
    }
}
