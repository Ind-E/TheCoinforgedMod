package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

// deal 14(18) damage. Deals 1 less damage for each card in your discard pile.
//TODO: implement damage reduction logic
public class ChippedBlade extends AbstractCoinforgedCard {
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(ChippedBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public ChippedBlade() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_HEAVY));
    }

}
