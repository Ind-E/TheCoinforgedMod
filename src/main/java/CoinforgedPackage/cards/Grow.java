package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

//TODO: implement upgrade logic
public class Grow extends AbstractReturnCard {
    private static final int MAGIC = 1;

    public static final String ID = makeID(Grow.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2);

    public Grow() {
        super(ID, info);
        setMagic(MAGIC);
    }

    public Grow(int cost) {
        this();
        this.cost = cost;
        this.costForTurn = cost;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
        Wiz.atb(new MakeTempCardInDrawPileAction(new Grow(this.costForTurn + 1), 1, true, true));
    }

}
