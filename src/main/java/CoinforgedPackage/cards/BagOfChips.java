package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.BagOfChipsAction;
import CoinforgedPackage.cards.chips.BasicChip;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class BagOfChips extends AbstractCoinforgedCard {

    public static final String ID = makeID(BagOfChips.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public BagOfChips() {
        super(ID, info, true);
        this.cardsToPreview = new BasicChip();
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new BagOfChipsAction(p));
    }

    @Override
    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<CardTags>();
        tags.add(CustomTags.POKER_CHIP);
        return tags;
    }

    // @Override
    // public List<String> getCardDescriptors() {
    //     return Collections.singletonList("test");
    // }
}