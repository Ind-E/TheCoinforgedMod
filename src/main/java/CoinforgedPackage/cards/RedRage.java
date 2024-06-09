package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.AdvantagePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class RedRage extends AbstractCoinforgedCard {
    private static final int ROLLS = 3;
    private static final int UPG_ROLLS = 1;
    private static final int MAGIC = 1;

    public float rotationTimer;
    public int previewIndex;
    public ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(RedRage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);

    public RedRage() {
        super(ID, info, true);
        setMagic(MAGIC);
        setCustomVar("R", ROLLS, UPG_ROLLS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new RollRedAction(p, customVar("R")));
        Wiz.atb(new ApplyPowerAction(p, p, new AdvantagePower(p, this.magicNumber)));
    }

    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }
}