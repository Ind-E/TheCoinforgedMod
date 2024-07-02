package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class RedRage extends AbstractCoinforgedCard {
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(RedRage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);

    public RedRage() {
        super(ID, info, true);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollRedAction(magicNumber));
    }

    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }
}