package GamblerMod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import GamblerMod.GamblerMod;
import static GamblerMod.GamblerMod.makeID;

public class RigPower extends BasePower implements InvisiblePower{
    public static final String POWER_ID = makeID("RigPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private AbstractPlayer p = AbstractDungeon.player;

    public RigPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractCard c : p.drawPile.group) {
            if (c.hasTag(GamblerMod.RIGGED)) {
                c.freeToPlayOnce = false;
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (c.hasTag(GamblerMod.RIGGED)) {
                c.freeToPlayOnce = false;
            }
        }
    }

}