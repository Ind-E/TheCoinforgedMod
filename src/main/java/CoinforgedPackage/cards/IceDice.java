package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class IceDice extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 1;
    private static final int WEAK = 1;

    public static final String ID = makeID(IceDice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1);

    public IceDice() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
        setCustomVar("weak", WEAK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollBlueAction(magicNumber));
        Wiz.applyToEnemy(m, new WeakPower(p, customVar("weak"), false));
    }

    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.BLUE_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceDice();
    }
}
