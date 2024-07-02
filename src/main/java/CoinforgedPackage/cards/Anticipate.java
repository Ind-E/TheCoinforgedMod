package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

import CoinforgedPackage.actions.RollPurpleAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

// card art idea: laying caltrops in front of sleeping lagavulin
public class Anticipate extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(Anticipate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public Anticipate() {
        super(ID, info, true);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollPurpleAction(magicNumber));
        Wiz.applyToSelf(new EquilibriumPower(p, 1));
    }

    @Override
    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.PURPLE_DIE);
        return tags;
    }
}