package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import CoinforgedPackage.actions.OverflowAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class ShiftyStrike extends AbstractCoinforgedCard {
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(ShiftyStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2);

    public ShiftyStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new DrawCardAction(1));
        Wiz.atb(new OverflowAction(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false))));
    }

    // @Override
    // public void triggerOnGlowCheck() {
    //     if (Wiz.adp().hand.size() >= BaseMod.MAX_HAND_SIZE - 1) {
    //         glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    //     } else {
    //         glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    //     }
    // }

}
