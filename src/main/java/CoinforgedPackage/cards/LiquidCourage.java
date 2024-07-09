package CoinforgedPackage.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField.ExhaustiveFields;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import CoinforgedPackage.actions.OverflowAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;
import basemod.BaseMod;

public class LiquidCourage extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int DEX = 1;
    private static final int EXHAUSTIVE = 2;

    public static final String ID = makeID(LiquidCourage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public LiquidCourage() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("dex", DEX);
        ExhaustiveFields.baseExhaustive.set(this, EXHAUSTIVE);
        ExhaustiveFields.exhaustive.set(this, EXHAUSTIVE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new DexterityPower(p, magicNumber));
        Wiz.applyToSelf(new LoseDexterityPower(p, magicNumber));
        Wiz.atb(new OverflowAction(new ApplyPowerAction(p, p, new DexterityPower(p, customVar("dex")))));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (Wiz.adp().hand.size() >= BaseMod.MAX_HAND_SIZE - 1) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

}
