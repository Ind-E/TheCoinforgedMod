package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.GrapesAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

//TODO: doesn't return cards in the same order sometimes
public class Grapes extends AbstractCoinforgedCard {
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(Grapes.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Grapes() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        setExhaust(true);
    }

    public Grapes(int cost, boolean upgraded) {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.baseCost = this.cost = cost;
        this.costForTurn = this.cost;
        if (upgraded) {
            this.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GrapesAction(this.magicNumber, this.upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Grapes();
    }
}