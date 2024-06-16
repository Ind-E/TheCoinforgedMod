package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.ModifiedHandSizePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class CoinBarrage extends AbstractCoinforgedCard {
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;
    private static final int HAND_SIZE_REDUCTION = 2;
    private static final int TIMES_TO_DEAL_DAMAGE = 7;

    public static final String ID = makeID(CoinBarrage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1);

    public CoinBarrage() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(HAND_SIZE_REDUCTION);
        setCustomVar("times", TIMES_TO_DEAL_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < TIMES_TO_DEAL_DAMAGE; i++) {
            Wiz.atb(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.05F));
            Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.NONE));
        }
        Wiz.atb(new ApplyPowerAction(p, p, new ModifiedHandSizePower(-magicNumber)));
    }
}
