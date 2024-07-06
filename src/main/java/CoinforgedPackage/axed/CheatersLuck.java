package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class CheatersLuck extends AbstractCoinforgedCard {
    private static final int CARDS_TO_DUPE = 1;
    private static final int UPG_CARDS_TO_DUPE = 1;

    public static final String ID = makeID(CheatersLuck.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1);

    public CheatersLuck() {
        super(ID, info);
        setMagic(CARDS_TO_DUPE, UPG_CARDS_TO_DUPE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.size() <= 1)
            return;

        copyRandomCard(p);
        if (upgraded) {
            copyRandomCard(p);
        }
    }

    public void copyRandomCard(AbstractPlayer p) {
        AbstractCard randomCardInHand = p.hand.group.get(AbstractDungeon.cardRandomRng.random(0, p.hand.size() - 1))
                .makeStatEquivalentCopy();
        while (randomCardInHand.originalName == this.originalName) {
            randomCardInHand = p.hand.group.get(AbstractDungeon.cardRandomRng.random(0, p.hand.size() - 1))
                    .makeStatEquivalentCopy();
        }
        randomCardInHand.setCostForTurn(0);
        Wiz.atb(new MakeTempCardInHandAction(randomCardInHand));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CheatersLuck();
    }
}