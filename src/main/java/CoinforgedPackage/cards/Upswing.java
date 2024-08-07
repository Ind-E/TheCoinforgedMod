package CoinforgedPackage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.util.Wiz;

public class Upswing extends AbstractCoinforgedCard {
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 1;
    private static final int DICE_TO_ROLL = 1;
    private static final int UPG_DICE_TO_ROLL = 1;

    public static final String ID = makeID(Upswing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public Upswing() {
        super(ID, info, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m,
                new DamageInfo(p, damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL),
                AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new RollBlueAction(magicNumber));
    }

    public List<CardTags> getPreviewTags() {
        List<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.BLUE_DIE);
        return tags;
    }

    public AbstractCard makeCopy() {
        return new Upswing();
    }
}