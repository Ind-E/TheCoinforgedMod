package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class CollectorsBlade extends AbstractCoinforgedCard {
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 1;

    public static final String ID = makeID(CollectorsBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public CollectorsBlade() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (m.hasPower(CripplingDebtPower.POWER_ID)) {
            Wiz.applyToEnemy(m, new StrengthPower(m, -magicNumber));
        }

    }
}