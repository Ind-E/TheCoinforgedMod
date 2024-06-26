package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class LongGame extends AbstractCoinforgedCard {
    private static final int DAMAGE = 35;
    private static final int UPG_DAMAGE = 5;
    private static final int TURNS_UNTIL_PLAYABLE = 5;
    private static final int UPG_TURNS_UNTIL_PLAYABLE = -1;
    private static final int STRENGTH = 3;

    public static final String ID = makeID(LongGame.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2);

    public LongGame() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(TURNS_UNTIL_PLAYABLE, UPG_TURNS_UNTIL_PLAYABLE);
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
        Wiz.atb(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH)));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new LongGame();
    }
}