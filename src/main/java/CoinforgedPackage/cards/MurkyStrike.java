package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.DontRenderIntentPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class MurkyStrike extends AbstractCoinforgedCard {
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 1;

    public static final String ID = makeID(MurkyStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public MurkyStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_DIAGONAL));
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.applyToEnemy(m, new DontRenderIntentPower(m, 2));
    }
}