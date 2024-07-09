package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.SetCostFollowUpAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

//TODO: seems quite strong
public class BlazingTactics extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int DRAW = 1;
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(BlazingTactics.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1);

    public BlazingTactics() {
        super(ID, info);
        setMagic(MAGIC);
        setCustomVar("draw", DRAW);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new DrawCardAction(customVar("draw"), new SetCostFollowUpAction(magicNumber, true)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlazingTactics();
    }
}
