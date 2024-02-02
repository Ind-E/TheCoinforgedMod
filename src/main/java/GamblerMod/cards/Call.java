package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.actions.CallAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Call extends BaseCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(Call.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.NONE, 
            1);

    public Call() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.discard = 2;
        this.baseDiscard = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.baseDiscard += 1;
            this.discard = this.baseDiscard;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, 1, false));
        addToBot(new CallAction(this, this.damage, this.magicNumber, this.discard));
    }

}