package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Dive extends AbstractReturnCard {
    private static final int MAGIC = 1;
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(Dive.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2);

    public Dive() {
        super(ID, info);
        setMagic(MAGIC);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.BLUNT_HEAVY));
        Wiz.applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
    }

}
