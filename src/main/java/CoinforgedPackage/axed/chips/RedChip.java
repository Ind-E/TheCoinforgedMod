package CoinforgedPackage.axed.chips;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.axed.AbstractRandomChipCard.ChipColor;
import CoinforgedPackage.util.Wiz;

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
        Wiz.atb(new DrawCardAction(p, this.magicNumber));
    }
}