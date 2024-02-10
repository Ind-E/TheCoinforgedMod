package GamblerMod.cards;

import GamblerMod.actions.RigAction;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.RigPower;
import GamblerMod.util.CardStats;

public class Rig extends BaseCard{
    private static final int MAGIC = 1;
    public static final String ID = makeID(Rig.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.NONE, 
            2
    );

    public Rig() {
        super(ID, info);
        setMagic(MAGIC);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RigAction());
        addToBot(new DrawCardAction(p, MAGIC));
        addToBot(new ApplyPowerAction(p, p, new RigPower(p)));
    }

}
