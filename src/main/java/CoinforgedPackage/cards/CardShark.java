package CoinforgedPackage.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class CardShark extends AbstractCoinforgedCard {
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;
    private AbstractPlayer p = AbstractDungeon.player;

    public static final String ID = makeID(CardShark.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2);

    public CardShark() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            Wiz.atb(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SKY)));
            for (int i = 0; i < 5; i++)
                Wiz.atb(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        } else {
            Wiz.atb(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SKY), 0.4F));
            for (int i = 0; i < 5; i++)
                Wiz.atb(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
        }
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void triggerOnGlowCheck() {
        p = AbstractDungeon.player;
        if (p.hand.getBottomCard().uuid == this.uuid) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            this.costForTurn = 0;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardShark();
    }
}
