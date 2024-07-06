package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConservePower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.PlayerAppliedDrawReductionPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Boooost extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(Boooost.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Boooost() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainEnergyAction(magicNumber));
        Wiz.applyToSelf(new ConservePower(p, 1));
        Wiz.applyToSelf(new PlayerAppliedDrawReductionPower(p, 1));
    }
}
