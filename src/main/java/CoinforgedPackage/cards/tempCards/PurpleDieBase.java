package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public abstract class PurpleDieBase extends AbstractCoinforgedCard {
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(PurpleDieBase.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public PurpleDieBase(String ID, int MAGIC) {
        this(ID, info, MAGIC, UPG_MAGIC);
    }

    public PurpleDieBase(String ID, CardStats info, int MAGIC, int UPG_MAGIC) {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(CustomTags.DIE);
        tags.add(CustomTags.PURPLE_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // if (Settings.FAST_MODE) {
        //     Wiz.atb(new VFXAction((AbstractCreature) p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        // } else {
        //     Wiz.atb(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.4F));
        // }
        Wiz.applyToSelf(new VigorPower(p, magicNumber));
    }

}
