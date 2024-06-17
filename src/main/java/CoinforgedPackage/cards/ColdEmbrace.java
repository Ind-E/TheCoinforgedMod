package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.ColdEmbraceAction;
import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

//TODO: game feel is bad because Defends hit by Buff aren't distinguishable from normal Defends
public class ColdEmbrace extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int BLOCK_BUFF = 1;

    public static final String ID = makeID(ColdEmbrace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);

    public ColdEmbrace() {
        super(ID, info, true);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("block", BLOCK_BUFF);
    }

    @Override
    public ArrayList<CardTags> getPreviewTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.BLUE_DIE);
        return tags;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollBlueAction(magicNumber));
        Wiz.atb(new ColdEmbraceAction(customVar("block")));
    }
}