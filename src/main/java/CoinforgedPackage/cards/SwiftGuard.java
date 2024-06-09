package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.ModifiedHandSizePower;

public class SwiftGuard extends AbstractCoinforgedCard {
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 3;

    public static final String ID = makeID(SwiftGuard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0);

    public SwiftGuard() {
        super(ID, info);
        setMagic(MAGIC);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, block));
        Wiz.applyToSelf(new ModifiedHandSizePower(-magicNumber));
    }
}