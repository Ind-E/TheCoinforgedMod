package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.StackedDeckPower;
import GamblerMod.util.CardStats;

public class StackedDeck extends BaseCard {

    public static final String ID = makeID(StackedDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public StackedDeck() {
        super(ID, info);
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            this.isInnate = true;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StackedDeckPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StackedDeck();
    }
}
