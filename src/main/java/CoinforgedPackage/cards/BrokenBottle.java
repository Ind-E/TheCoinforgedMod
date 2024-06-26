package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.BrokenBottleAction;
import CoinforgedPackage.cards.tempCards.GlassShard;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class BrokenBottle extends AbstractCoinforgedCard {
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    

    public static final String ID = makeID(BrokenBottle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            X_COST);

    public BrokenBottle() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        damageType = damageTypeForTurn = DamageInfo.DamageType.NORMAL;
        cardsToPreview = new GlassShard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new BrokenBottleAction(m, damage, damageTypeForTurn, freeToPlayOnce, energyOnUse));
        Wiz.atb(new MakeTempCardInHandAction(new GlassShard(), 1));
    }
}