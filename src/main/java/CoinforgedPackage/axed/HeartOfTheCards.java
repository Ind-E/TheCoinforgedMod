package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import CoinforgedPackage.actions.HeartOfTheCardsFollowUpAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class HeartOfTheCards extends AbstractCoinforgedCard {
    public static final String ID = makeID(HeartOfTheCards.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            X_COST);

    public HeartOfTheCards() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = upgraded ? this.energyOnUse + 1 : this.energyOnUse;
        Wiz.atb(new DrawCardAction(effect, new HeartOfTheCardsFollowUpAction()));

        if (!this.freeToPlayOnce)
            p.energy.use(EnergyPanel.totalCount);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeartOfTheCards();
    }
}
