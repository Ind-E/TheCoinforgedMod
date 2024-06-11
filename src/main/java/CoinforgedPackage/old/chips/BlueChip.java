package CoinforgedPackage.old.chips;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.old.AbstractRandomChipCard.ChipColor;
import CoinforgedPackage.util.Wiz;

public class BlueChip extends BaseChip {

    public static final String ID = makeID(BlueChip.class.getSimpleName());

    private static final int BLOCK = 3;

    public BlueChip() {
        super(ID, info);
        this.canUse = true;
        setBlock(BLOCK);
        this.chipColor = ChipColor.Blue;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, this.block));
    }
}