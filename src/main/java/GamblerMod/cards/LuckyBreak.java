package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.actions.LuckyBreakAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class LuckyBreak extends BaseCard {
    private static final int DEXTERITY = 1;
    private static final int UPG_DEXTERITY = 1;
    private static final int CARD_DRAW = 2;

    public static final String ID = makeID(LuckyBreak.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        1);

    public LuckyBreak() {
        super(ID, info);
        setMagic(DEXTERITY, UPG_DEXTERITY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(CARD_DRAW));
        addToBot(new LuckyBreakAction(this.magicNumber));
    }

}
