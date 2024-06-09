package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.OverflowingArmorPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class OverflowingArmor extends AbstractCoinforgedCard {
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public static final String ID = makeID(OverflowingArmor.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public OverflowingArmor() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new OverflowingArmorPower(magicNumber)));
    }

}
