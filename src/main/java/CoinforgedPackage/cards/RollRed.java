package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class RollRed extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 1;
    private static final int TEMP_STR = 1;

    public static final String ID = makeID(RollRed.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);

    public RollRed() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
        setCustomVar("str", TEMP_STR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, customVar("str")));
        Wiz.applyToSelf(new LoseStrengthPower(p, customVar("str")));
        Wiz.atb(new RollRedAction(magicNumber));
    }

    @Override
    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RollRed();
    }
}
