package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import CoinforgedPackage.actions.OverflowAction;
import CoinforgedPackage.character.Coinforged;

public class TwinSlash extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public static final String ID = makeID(TwinSlash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);

    public TwinSlash() {
        super(ID, info);
        setMagic(MAGIC);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_VERTICAL));
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new OverflowAction(new DrawCardAction(magicNumber)));
    }
}