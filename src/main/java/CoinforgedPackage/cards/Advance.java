package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.PlayerAppliedDrawReductionPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Advance extends AbstractCoinforgedCard {
    private static final int CARD_DRAW = 2;
    private static final int UPG_CARD_DRAW = 1;
    private static final int DRAW_REDUCTION = 1;

    public static final String ID = makeID(Advance.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            0);

    public Advance() {
        super(ID, info);
        setMagic(CARD_DRAW, UPG_CARD_DRAW);
        setCustomVar("drawDown", DRAW_REDUCTION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(p, this.magicNumber));
        addToTop(new ApplyPowerAction(p, p, new PlayerAppliedDrawReductionPower(p, customVar("drawDown"))));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Advance();
    }

}
