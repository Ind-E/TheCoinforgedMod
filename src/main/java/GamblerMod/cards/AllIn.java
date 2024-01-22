package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.powers.AllInPower;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class AllIn extends BaseCard{
    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 5;

    public static final String ID = makeID(AllIn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.BASIC, 
            CardTarget.ENEMY, 
            2 
    );

    public AllIn() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p, p, new AllInPower(p)));        
    }

}
