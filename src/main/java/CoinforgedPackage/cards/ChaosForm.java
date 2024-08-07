package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.ChaosFormPower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class ChaosForm extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;

    public static final String ID = makeID(ChaosForm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3);

    public ChaosForm() {
        super(ID, info, true);
        setMagic(MAGIC);
        setEthereal(true, false);
        this.timerDuration = 1.5F;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new ChaosFormPower(p)));
    }

    @Override
    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.MAGIC_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChaosForm();
    }
}
