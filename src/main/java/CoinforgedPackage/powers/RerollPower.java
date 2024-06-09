package CoinforgedPackage.powers;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.Wiz;

public class RerollPower extends BasePower {
    public static final String POWER_ID = makeID(RerollPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private HashMap<String, Boolean> bonusRolls = new HashMap<>();
    private boolean ready = false;

    public RerollPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        bonusRolls.put("One", false);
        bonusRolls.put("Two", false);
        bonusRolls.put("Three", false);
        bonusRolls.put("Four", false);
        bonusRolls.put("Five", false);
        bonusRolls.put("Six", false);
        updateDescription();
    }

    public void updateDescription() {
        if (bonusRolls == null){
            this.description = DESCRIPTIONS[0];
            return;
        }
        else if (ready) {
            this.description = DESCRIPTIONS[2];
            return;
        }
        StringBuilder descriptionBuilder = new StringBuilder();

        for (HashMap.Entry<String, Boolean> entry : bonusRolls.entrySet()) {
            if (!entry.getValue()) {
                Integer number = wordToNumber(entry.getKey());
                if (number != null) {
                    descriptionBuilder.append("#b" + number + " [REMOVE_SPACE]").append(", ");
                }
            }
        }

        // Remove trailing comma
        if (descriptionBuilder.length() > 0) {
            descriptionBuilder.setLength(descriptionBuilder.length() - 2);
        }

        this.description = DESCRIPTIONS[1] + descriptionBuilder.toString();
    }

    private Integer wordToNumber(String word) {
        switch (word.toLowerCase()) {
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            default:
                return null;
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(CustomTags.DIE)) {
            Pattern pattern = Pattern.compile("[A-Z][a-z]*$");
            Matcher matcher = pattern.matcher(card.cardID);
            if (matcher.find()) {
                bonusRolls.put(matcher.group(), true);
                if (bonusRolls.values().stream().allMatch(b -> b) && !ready) {
                    flash();
                    ready = true;
                    AbstractDungeon.overlayMenu.endTurnButton.updateText(DESCRIPTIONS[3]);
                }
                updateDescription();
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            boolean allTrue = bonusRolls.values().stream().allMatch(b -> b);
            if (allTrue) {
                Wiz.atb(new SkipEnemiesTurnAction());
                ready = false;
                for (String key : bonusRolls.keySet()) {
                    bonusRolls.put(key, false);
                }
                updateDescription();
            }
        }
    }
}