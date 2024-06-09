package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class BackBurner extends AbstractCoinforgedCard {
    private static final int MAGIC = 4;
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;
    private static final int BLOCK = 0;

    public static final String ID = makeID(BackBurner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1);

    public BackBurner() {
        super(ID, info);
        setMagic(MAGIC);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        Wiz.atb(new GainBlockAction(p, this.block));
        Wiz.atb(new ModifyBlockAction(this.uuid, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new BackBurner();
    }

}
