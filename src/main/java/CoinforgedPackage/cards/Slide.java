package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import CoinforgedPackage.actions.OverflowAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Slide extends AbstractCoinforgedCard {
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;

    public static final String ID = makeID(Slide.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2);

    public Slide() {
        super(ID, info);
        setMagic(MAGIC);
        setBlock(BLOCK, UPG_BLOCK);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, block));
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new OverflowAction(new ApplyPowerAction(p, p, (new ArtifactPower(p, magicNumber)))));
    }
}