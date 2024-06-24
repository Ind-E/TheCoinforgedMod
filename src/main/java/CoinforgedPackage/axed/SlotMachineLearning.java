package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.SlotMachineLearningPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class SlotMachineLearning extends AbstractCoinforgedCard {
    private static final int MAGIC = 3;

    public static final String ID = makeID(SlotMachineLearning.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public SlotMachineLearning() {
        super(ID, info);
        setMagic(MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new SlotMachineLearningPower(1)));
    }

}
