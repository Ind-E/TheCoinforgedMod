package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Spinwarden.actions.UnleashedStrategyAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.ModifiedHandSizePower;
import Spinwarden.util.CardStats;

public class UnleashedStrategy extends BaseCard {
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(UnleashedStrategy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0);

    public UnleashedStrategy() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ModifiedHandSizePower(p, this.magicNumber), this.magicNumber));
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new UnleashedStrategyAction());
    }
}