package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

public class RedChip extends BaseChip {

    public static final String ID = makeID(RedChip.class.getSimpleName());

    private static final int MAGIC = 1;

    public RedChip() {
        super(ID, info);
        this.canUse = true;
        setMagic(MAGIC);
        this.chipColor = ChipColor.Red;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
    }
}