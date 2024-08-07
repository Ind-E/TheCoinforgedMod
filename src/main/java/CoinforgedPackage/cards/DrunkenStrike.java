package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class DrunkenStrike extends AbstractCoinforgedCard {
    private static final int DAMAGE = 16;
    private static final int UPG_DAMAGE = 5;

    public static final String ID = makeID(DrunkenStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            2);

    public DrunkenStrike() {
        super(ID, info);
        this.damageType = DamageInfo.DamageType.NORMAL;
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void triggerWhenDrawn() {
        costForTurn = getRandomCost();
    }

    private int getRandomCost() {
        return AbstractDungeon.cardRandomRng.random(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DrunkenStrike();
    }
}
