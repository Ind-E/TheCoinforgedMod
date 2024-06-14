package CoinforgedPackage.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField.ExhaustiveFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Unflex extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;
    private static final int EXHAUSTIVE_USES = 2;

    public static final String ID = makeID(Unflex.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            0);

    public Unflex() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        ExhaustiveFields.baseExhaustive.set(this, EXHAUSTIVE_USES);
        ExhaustiveFields.exhaustive.set(this, EXHAUSTIVE_USES);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            Wiz.applyToEnemy(mo, new StrengthPower(mo, -magicNumber));
        }
        Wiz.applyToSelf(new StrengthPower(p, -magicNumber));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.hasPower(ArtifactPower.POWER_ID)) {
                Wiz.applyToEnemy(mo, new GainStrengthPower(mo, magicNumber));
            }
        }
        if (!p.hasPower(ArtifactPower.POWER_ID)) {
            Wiz.applyToSelf(new GainStrengthPower(p, magicNumber));
        }
    }

}