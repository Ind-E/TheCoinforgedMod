package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;
import CoinforgedPackage.actions.OverflowAction;
import CoinforgedPackage.character.Coinforged;

public class TwinSlash extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public static final String ID = makeID(TwinSlash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public TwinSlash() {
        super(ID, info);
        setMagic(MAGIC);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_VERTICAL));
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new OverflowAction(new DrawCardAction(magicNumber)));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (Wiz.adp().hand.size() >= BaseMod.MAX_HAND_SIZE - 1) {
            // glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            // glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}