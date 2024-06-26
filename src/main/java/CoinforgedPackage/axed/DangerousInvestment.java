package CoinforgedPackage.axed;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.DangerousInvestmentPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class DangerousInvestment extends AbstractCoinforgedCard{
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(DangerousInvestment.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    public DangerousInvestment() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(m, p, new StrengthPower(m, this.magicNumber)));
        Wiz.atb(new ApplyPowerAction(m, p, new DangerousInvestmentPower(m, this.magicNumber)));
    }
}