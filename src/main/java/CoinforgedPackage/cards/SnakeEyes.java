package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.SnakeEyesAction;
import CoinforgedPackage.cards.tempCards.BlueOne;
import CoinforgedPackage.cards.tempCards.GreenOne;
import CoinforgedPackage.cards.tempCards.PurpleOne;
import CoinforgedPackage.cards.tempCards.RedOne;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class SnakeEyes extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;

    public static final String ID = makeID(SnakeEyes.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public SnakeEyes() {
        super(ID, info, true);
        setMagic(MAGIC);
        setCostUpgrade(0);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SnakeEyesAction());

    }

    @Override
    public List<AbstractCard> getList() {
        List<AbstractCard> OnesList = new ArrayList<>();
        OnesList.add(new GreenOne().makeCopy());
        OnesList.add(new BlueOne().makeCopy());
        OnesList.add(new RedOne().makeCopy());
        OnesList.add(new PurpleOne().makeCopy());
        return OnesList;
    }

    @Override
    public AbstractCard makeCopy() {
        return new SnakeEyes();
    }
}
