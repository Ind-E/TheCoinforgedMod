package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.optionCards.AttackFromDeck;
import CoinforgedPackage.cards.optionCards.PowerFromDeck;
import CoinforgedPackage.cards.optionCards.SkillFromDeck;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class JokerTrick extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = -1;

    public static final String ID = makeID(JokerTrick.class.getSimpleName());

    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1);

    public JokerTrick() {
        super(ID, info);
        setExhaust(true);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardChoices = new ArrayList<AbstractCard>();
        cardChoices.add(new AttackFromDeck(upgraded));
        cardChoices.add(new SkillFromDeck(upgraded));
        cardChoices.add(new PowerFromDeck(upgraded));
        Wiz.atb(new ChooseOneAction(cardChoices));
    }

    @Override
    public AbstractCard makeCopy() {
        return new JokerTrick();
    }
}