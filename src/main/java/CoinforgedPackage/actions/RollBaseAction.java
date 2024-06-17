package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.powers.LoadedDicePower;
import CoinforgedPackage.powers.SnakeEyesPower;
import CoinforgedPackage.util.Wiz;

public abstract class RollBaseAction extends AbstractGameAction {
    public int minroll = 1;
    public int maxroll = 6;
    public int magic = 0;
    public AbstractPlayer player;

    public RollBaseAction(int magic) {
        this(magic, 1, 6);
    }

    public RollBaseAction(int magic, int minroll, int maxroll) {
        this.player = AbstractDungeon.player;
        this.magic = magic;
        this.minroll = minroll;
        this.maxroll = maxroll;
    }

    public abstract AbstractCard roll();

    public void update() {
        for (int i = 0; i < magic; i++) {
            AbstractCard rolledCard = roll();
            if (player.hasPower(SnakeEyesPower.POWER_ID)) {
                Wiz.att(new ReducePowerAction(player, player, SnakeEyesPower.POWER_ID, 1));
            }
            if (player.hasPower(LoadedDicePower.POWER_ID)) {
                int amount = player.getPower(LoadedDicePower.POWER_ID).amount;
                rolledCard.baseMagicNumber += amount;
                rolledCard.magicNumber = rolledCard.baseMagicNumber;
                rolledCard.baseDamage += amount;
                rolledCard.damage = rolledCard.baseDamage;
                rolledCard.baseBlock += amount;
                rolledCard.block = rolledCard.baseBlock;

                rolledCard.initializeDescription();
            }
            Wiz.att(new MakeTempCardInHandAction(rolledCard, 1));
        }
        this.isDone = true;
    }
}
