package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.SpendChipsAction;
import CoinforgedPackage.character.Coinforged;

// feels weak
public class FinalBet extends AbstractCoinforgedCard{
    private static final int DAMAGE = 24;
    private static final int UPG_DAMAGE = 8;

    public static final String ID = makeID(FinalBet.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.RARE,
        CardTarget.ENEMY,
        2
    );

    public FinalBet() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int chips = getNumChips();
        addToBot(new SpendChipsAction(chips, true));
        for (int i = 0; i < chips; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (getNumChips() >= 1) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinalBet();
    }
}