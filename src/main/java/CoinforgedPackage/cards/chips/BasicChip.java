package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

public class BasicChip extends BaseChip {

    public static final String ID = makeID(BasicChip.class.getSimpleName());

    public BasicChip() {
        super(ID, info);
        this.chipColor = ChipColor.White;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}