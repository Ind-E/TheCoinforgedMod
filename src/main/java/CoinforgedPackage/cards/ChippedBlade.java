package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class ChippedBlade extends AbstractCoinforgedCard {
    private static final int DAMAGE = 13;
    private static final int UPG_DAMAGE = 5;
    private static final int MAGIC = 0;

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
        setMagic(MAGIC);
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = baseDamage;
        baseMagicNumber = Wiz.adp().discardPile.size();
        baseDamage -= baseMagicNumber;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseMagicNumber = Wiz.adp().discardPile.size();
        int realBaseDamage = baseDamage;
        baseDamage -= baseMagicNumber;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_HEAVY));
    }

}
