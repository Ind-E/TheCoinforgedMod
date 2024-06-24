package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.actions.RollGreenAction;
import CoinforgedPackage.actions.RollPurpleAction;
import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.cards.tempCards.BlueOne;
import CoinforgedPackage.cards.tempCards.GreenOne;
import CoinforgedPackage.cards.tempCards.PurpleOne;
import CoinforgedPackage.cards.tempCards.RedOne;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

//TODO: add upgrade
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
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < BaseMod.MAX_HAND_SIZE - Wiz.adp().hand.size() + 1; i++) {
            switch (AbstractDungeon.cardRandomRng.random(0, 4)) {
                case 0:
                    Wiz.atb(new RollBlueAction(1, 1, 1));
                    break;
                case 1:
                    Wiz.atb(new RollRedAction(1, 1, 1));
                    break;
                case 2:
                    Wiz.atb(new RollGreenAction(1, 1, 1));
                    break;
                case 3:
                    Wiz.atb(new RollPurpleAction(1, 1, 1));
                    break;
                default:
                    Wiz.atb(new RollPurpleAction(1, 2, 2));
                    break;
            }
        }
    }

    @Override
    public ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> OnesList = new ArrayList<>();
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
