package CoinforgedPackage.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField.ExhaustiveFields;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class StackedDeck extends AbstractCoinforgedCard {
    private static final int MAGIC = 10;

    public static final String ID = makeID(StackedDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public StackedDeck() {
        super(ID, info);
        setMagic(MAGIC);
        setExhaust(true, false);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            ExhaustiveFields.baseExhaustive.set(this, 2);
            ExhaustiveFields.exhaustive.set(this, 2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ScryAction(magicNumber));
    }
}
