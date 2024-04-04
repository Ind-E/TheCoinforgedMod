package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

//when spent, increase hand size by 1
public class OrangeChip extends BaseChip {

    private static final int MAGIC = 1;
    public static final String ID = makeID(OrangeChip.class.getSimpleName());

    public OrangeChip() {
        super(ID, info);
        this.chipColor = ChipColor.Orange;
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}