package CoinforgedPackage.cards;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.CardModifierManager;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.CoinforgedMain;
import CoinforgedPackage.modifiers.MakeBlackChipModifier;
import CoinforgedPackage.modifiers.MakeBlueChipModifier;
import CoinforgedPackage.modifiers.MakeCrackedChipModifier;
import CoinforgedPackage.modifiers.MakeGrayChipModifier;
import CoinforgedPackage.modifiers.MakeGreenChipModifier;
import CoinforgedPackage.modifiers.MakeOrangeChipModifier;
import CoinforgedPackage.modifiers.MakeRedChipModifier;
import CoinforgedPackage.modifiers.MakeWhiteChipModifier;
import CoinforgedPackage.util.CardStats;

import static CoinforgedPackage.util.GeneralUtils.removePrefix;
import static CoinforgedPackage.util.TextureLoader.getCardTextureString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

public abstract class AbstractCoinforgedCard extends CustomCard {
    final private static Map<String, DynamicVariable> customVars = new HashMap<>();

    protected static String makeID(String name) {
        return CoinforgedMain.makeID(name);
    }

    protected CardStrings cardStrings;

    protected boolean upgradesDescription;
    protected boolean hasChipModifier = false;

    protected int baseCost;

    protected boolean upgradeCost;
    protected int costUpgrade;

    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;

    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;

    protected boolean baseExhaust = false;
    protected boolean upgExhaust = false;
    protected boolean baseEthereal = false;
    protected boolean upgEthereal = false;
    protected boolean baseInnate = false;
    protected boolean upgInnate = false;
    protected boolean baseRetain = false;
    protected boolean upgRetain = false;

    private Map<AbstractCardModifier, Integer> chipModifiers = new HashMap<>();

    final protected Map<String, LocalVarInfo> cardVariables = new HashMap<>();

    public AbstractCoinforgedCard(String ID, CardStats info) {
        this(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor);
    }

    public AbstractCoinforgedCard(String ID, CardStats info, boolean upgradesDescription) {
        this(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor, upgradesDescription);
    }

    public AbstractCoinforgedCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color) {
        super(ID, getName(ID), getCardTextureString(removePrefix(ID), cardType), cost, getInitialDescription(ID),
                cardType, color, rarity, target);
        this.cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
        this.originalName = cardStrings.NAME;

        this.baseCost = cost;

        this.upgradesDescription = cardStrings.UPGRADE_DESCRIPTION != null;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;
    }

    public AbstractCoinforgedCard(String ID, CardStats info, Boolean addChipModifier) {
        this(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor);
        chipModifiers.put(new MakeOrangeChipModifier(), 3);
        chipModifiers.put(new MakeBlackChipModifier(), 2);
        chipModifiers.put(new MakeWhiteChipModifier(), 5);
        chipModifiers.put(new MakeGrayChipModifier(), 4);
        chipModifiers.put(new MakeCrackedChipModifier(), 1);
        chipModifiers.put(new MakeBlueChipModifier(), 3);
        chipModifiers.put(new MakeRedChipModifier(), 3);
        chipModifiers.put(new MakeGreenChipModifier(), 10);

        if (addChipModifier && !hasChipModifier) {
            CardModifierManager.addModifier(this, getRandomChipModifier());
            hasChipModifier = true;
        }
    }

    public AbstractCardModifier getRandomChipModifier() {
        ArrayList<AbstractCardModifier> weightedList = new ArrayList<>();
        for (Map.Entry<AbstractCardModifier, Integer> entry : chipModifiers.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                weightedList.add(entry.getKey());
            }
        }
        int randomIndex = new Random().nextInt(weightedList.size());
        return weightedList.get(randomIndex);
    }

    public AbstractCoinforgedCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color,
            boolean upgradesDescription) {
        this(ID, cost, cardType, target, rarity, color);
        this.upgradesDescription = upgradesDescription;
    }

    private static String getName(String ID) {
        return CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    }

    private static String getInitialDescription(String ID) {
        return CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
    }

    // Methods meant for constructor use
    protected final void setDamage(int damage) {
        this.setDamage(damage, 0);
    }

    protected final void setDamage(int damage, int damageUpgrade) {
        this.baseDamage = this.damage = damage;
        if (damageUpgrade != 0) {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
        }
    }

    protected final void setBlock(int block) {
        this.setBlock(block, 0);
    }

    protected final void setBlock(int block, int blockUpgrade) {
        this.baseBlock = this.block = block;
        if (blockUpgrade != 0) {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
        }
    }

    protected final void setMagic(int magic) {
        this.setMagic(magic, 0);
    }

    protected final void setMagic(int magic, int magicUpgrade) {
        this.baseMagicNumber = this.magicNumber = magic;
        if (magicUpgrade != 0) {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
        }
    }

    protected final void setCustomVar(String key, int base) {
        this.setCustomVar(key, base, 0);
    }

    protected final void setCustomVar(String key, int base, int upgrade) {
        setCustomVarValue(key, base, upgrade);

        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key);
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
            initializeDescription();
        }
    }

    protected enum VariableType {
        DAMAGE,
        BLOCK,
        MAGIC
    }

    protected final void setCustomVar(String key, VariableType type, int base) {
        setCustomVar(key, type, base, 0);
    }

    protected final void setCustomVar(String key, VariableType type, int base, int upgrade) {
        setCustomVarValue(key, base, upgrade);

        switch (type) {
            case DAMAGE:
                calculateVarAsDamage(key);
                break;
            case BLOCK:
                calculateVarAsBlock(key);
                break;
            case MAGIC:
                break;
        }

        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key);
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
            initializeDescription();
        }
    }

    protected final void setCustomVar(String key, VariableType type, int base,
            BiFunction<AbstractMonster, Integer, Integer> preCalc) {
        setCustomVar(key, type, base, 0, preCalc);
    }

    protected final void setCustomVar(String key, VariableType type, int base, int upgrade,
            BiFunction<AbstractMonster, Integer, Integer> preCalc) {
        setCustomVar(key, type, base, upgrade, preCalc, (m, val) -> val);
    }

    protected final void setCustomVar(String key, VariableType type, int base,
            BiFunction<AbstractMonster, Integer, Integer> preCalc,
            BiFunction<AbstractMonster, Integer, Integer> postCalc) {
        setCustomVar(key, type, base, 0, preCalc, postCalc);
    }

    protected final void setCustomVar(String key, VariableType type, int base, int upgrade,
            BiFunction<AbstractMonster, Integer, Integer> preCalc,
            BiFunction<AbstractMonster, Integer, Integer> postCalc) {
        setCustomVarValue(key, base, upgrade);

        switch (type) {
            case DAMAGE:
                setVarCalculation(key, (m, baseVal) -> {
                    boolean wasMultiDamage = this.isMultiDamage;
                    this.isMultiDamage = false;

                    int tmp = this.baseDamage;

                    this.baseDamage = baseVal;

                    this.baseDamage = preCalc.apply(m, this.baseDamage);

                    if (m != null)
                        super.calculateCardDamage(m);
                    else
                        super.applyPowers();

                    this.damage = postCalc.apply(m, this.damage);

                    this.baseDamage = tmp;
                    this.isMultiDamage = wasMultiDamage;

                    return damage;
                });
                break;
            case BLOCK:
                setVarCalculation(key, (m, baseVal) -> {
                    int tmp = this.baseBlock;

                    this.baseBlock = baseVal;

                    this.baseBlock = preCalc.apply(m, this.baseBlock);

                    if (m != null)
                        super.calculateCardDamage(m);
                    else
                        super.applyPowers();

                    this.block = postCalc.apply(m, this.block);

                    this.baseBlock = tmp;
                    return block;
                });
                break;
            default:
                setVarCalculation(key, (m, baseVal) -> {
                    int tmp = baseVal;

                    tmp = preCalc.apply(m, tmp);
                    tmp = postCalc.apply(m, tmp);

                    return tmp;
                });
                break;
        }

        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key);
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
            initializeDescription();
        }
    }

    private void setCustomVarValue(String key, int base, int upg) {
        cardVariables.compute(key, (k, old) -> {
            if (old == null) {
                return new LocalVarInfo(base, upg);
            } else {
                old.base = base;
                old.upgrade = upg;
                return old;
            }
        });
    }

    protected final void colorCustomVar(String key, Color normalColor) {
        colorCustomVar(key, normalColor, Settings.GREEN_TEXT_COLOR, Settings.RED_TEXT_COLOR, Settings.GREEN_TEXT_COLOR);
    }

    protected final void colorCustomVar(String key, Color normalColor, Color increasedColor, Color decreasedColor) {
        colorCustomVar(key, normalColor, increasedColor, decreasedColor, increasedColor);
    }

    protected final void colorCustomVar(String key, Color normalColor, Color increasedColor, Color decreasedColor,
            Color upgradedColor) {
        LocalVarInfo var = getCustomVar(key);
        if (var == null) {
            throw new IllegalArgumentException("Attempted to set color of variable that hasn't been registered.");
        }

        var.normalColor = normalColor;
        var.increasedColor = increasedColor;
        var.decreasedColor = decreasedColor;
        var.upgradedColor = upgradedColor;
    }

    private LocalVarInfo getCustomVar(String key) {
        return cardVariables.get(key);
    }

    protected void calculateVarAsDamage(String key) {
        setVarCalculation(key, (m, base) -> {
            boolean wasMultiDamage = this.isMultiDamage;
            this.isMultiDamage = false;

            int tmp = this.baseDamage;

            this.baseDamage = base;
            if (m != null)
                super.calculateCardDamage(m);
            else
                super.applyPowers();

            this.baseDamage = tmp;
            this.isMultiDamage = wasMultiDamage;

            return damage;
        });
    }

    protected void calculateVarAsBlock(String key) {
        setVarCalculation(key, (m, base) -> {
            int tmp = this.baseBlock;

            this.baseBlock = base;
            if (m != null)
                super.calculateCardDamage(m);
            else
                super.applyPowers();

            this.baseBlock = tmp;
            return block;
        });
    }

    protected void setVarCalculation(String key, BiFunction<AbstractMonster, Integer, Integer> calculation) {
        cardVariables.get(key).calculation = calculation;
    }

    public int customVarBase(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return -1;
        return var.base;
    }

    public int customVar(String key) {
        LocalVarInfo var = cardVariables == null ? null : cardVariables.get(key); // Prevents crashing when used with
                                                                                  // dynamic text
        if (var == null)
            return -1;
        return var.value;
    }

    public int[] customVarMulti(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return null;
        return var.aoeValue;
    }

    public boolean isCustomVarModified(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return false;
        return var.isModified();
    }

    public boolean customVarUpgraded(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return false;
        return var.upgraded;
    }

    protected final void setCostUpgrade(int costUpgrade) {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }

    protected final void setExhaust(boolean exhaust) {
        this.setExhaust(exhaust, exhaust);
    }

    protected final void setEthereal(boolean ethereal) {
        this.setEthereal(ethereal, ethereal);
    }

    protected final void setInnate(boolean innate) {
        this.setInnate(innate, innate);
    }

    protected final void setSelfRetain(boolean retain) {
        this.setSelfRetain(retain, retain);
    }

    protected final void setExhaust(boolean baseExhaust, boolean upgExhaust) {
        this.baseExhaust = baseExhaust;
        this.upgExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }

    protected final void setEthereal(boolean baseEthereal, boolean upgEthereal) {
        this.baseEthereal = baseEthereal;
        this.upgEthereal = upgEthereal;
        this.isEthereal = baseEthereal;
    }

    protected void setInnate(boolean baseInnate, boolean upgInnate) {
        this.baseInnate = baseInnate;
        this.upgInnate = upgInnate;
        this.isInnate = baseInnate;
    }

    protected void setSelfRetain(boolean baseRetain, boolean upgRetain) {
        this.baseRetain = baseRetain;
        this.upgRetain = upgRetain;
        this.selfRetain = baseRetain;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();

        if (card instanceof AbstractCoinforgedCard) {
            card.rawDescription = this.rawDescription;
            ((AbstractCoinforgedCard) card).upgradesDescription = this.upgradesDescription;

            ((AbstractCoinforgedCard) card).baseCost = this.baseCost;

            ((AbstractCoinforgedCard) card).upgradeCost = this.upgradeCost;
            ((AbstractCoinforgedCard) card).upgradeDamage = this.upgradeDamage;
            ((AbstractCoinforgedCard) card).upgradeBlock = this.upgradeBlock;
            ((AbstractCoinforgedCard) card).upgradeMagic = this.upgradeMagic;

            ((AbstractCoinforgedCard) card).costUpgrade = this.costUpgrade;
            ((AbstractCoinforgedCard) card).damageUpgrade = this.damageUpgrade;
            ((AbstractCoinforgedCard) card).blockUpgrade = this.blockUpgrade;
            ((AbstractCoinforgedCard) card).magicUpgrade = this.magicUpgrade;

            ((AbstractCoinforgedCard) card).baseExhaust = this.baseExhaust;
            ((AbstractCoinforgedCard) card).upgExhaust = this.upgExhaust;
            ((AbstractCoinforgedCard) card).baseEthereal = this.baseEthereal;
            ((AbstractCoinforgedCard) card).upgEthereal = this.upgEthereal;
            ((AbstractCoinforgedCard) card).baseInnate = this.baseInnate;
            ((AbstractCoinforgedCard) card).upgInnate = this.upgInnate;
            ((AbstractCoinforgedCard) card).baseRetain = this.baseRetain;
            ((AbstractCoinforgedCard) card).upgRetain = this.upgRetain;

            for (Map.Entry<String, LocalVarInfo> varEntry : cardVariables.entrySet()) {
                LocalVarInfo target = ((AbstractCoinforgedCard) card).getCustomVar(varEntry.getKey()),
                        current = varEntry.getValue();
                if (target == null) {
                    ((AbstractCoinforgedCard) card).setCustomVar(varEntry.getKey(), current.base, current.upgrade);
                    target = ((AbstractCoinforgedCard) card).getCustomVar(varEntry.getKey());
                }
                target.base = current.base;
                target.value = current.value;
                target.aoeValue = current.aoeValue;
                target.upgrade = current.upgrade;
                target.calculation = current.calculation;
            }
        }

        return card;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();

            if (this.upgradesDescription) {
                if (cardStrings.UPGRADE_DESCRIPTION == null) {
                    CoinforgedMain.logger
                            .error("Card " + cardID + " upgrades description and has null upgrade description.");
                } else {
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                }
            }

            if (upgradeCost) {
                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
                    int diff = this.costUpgrade - this.baseCost; // how the upgrade alters cost
                    this.upgradeBaseCost(this.cost + diff);
                    if (this.cost < 0)
                        this.cost = 0;
                } else {
                    upgradeBaseCost(costUpgrade);
                }
            }

            if (upgradeDamage)
                this.upgradeDamage(damageUpgrade);

            if (upgradeBlock)
                this.upgradeBlock(blockUpgrade);

            if (upgradeMagic)
                this.upgradeMagicNumber(magicUpgrade);

            for (LocalVarInfo var : cardVariables.values()) {
                if (var.upgrade != 0) {
                    var.base += var.upgrade;
                    var.value = var.base;
                    var.upgraded = true;
                }
            }

            if (baseExhaust ^ upgExhaust)
                this.exhaust = upgExhaust;

            if (baseInnate ^ upgInnate)
                this.isInnate = upgInnate;

            if (baseEthereal ^ upgEthereal)
                this.isEthereal = upgEthereal;

            if (baseRetain ^ upgRetain)
                this.selfRetain = upgRetain;

            this.initializeDescription();
        }
    }

    boolean inCalc = false;

    @Override
    public void applyPowers() {
        if (!inCalc) {
            inCalc = true;
            for (LocalVarInfo var : cardVariables.values()) {
                var.value = var.calculation.apply(null, var.base);
            }
            if (isMultiDamage) {
                ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
                AbstractMonster m;
                for (LocalVarInfo var : cardVariables.values()) {
                    if (var.aoeValue == null || var.aoeValue.length != monsters.size())
                        var.aoeValue = new int[monsters.size()];

                    for (int i = 0; i < monsters.size(); ++i) {
                        m = monsters.get(i);
                        var.aoeValue[i] = var.calculation.apply(m, var.base);
                    }
                }
            }
            inCalc = false;
        }

        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        if (!inCalc) {
            inCalc = true;
            for (LocalVarInfo var : cardVariables.values()) {
                var.value = var.calculation.apply(m, var.base);
            }
            if (isMultiDamage) {
                ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
                for (LocalVarInfo var : cardVariables.values()) {
                    if (var.aoeValue == null || var.aoeValue.length != monsters.size())
                        var.aoeValue = new int[monsters.size()];

                    for (int i = 0; i < monsters.size(); ++i) {
                        m = monsters.get(i);
                        var.aoeValue[i] = var.calculation.apply(m, var.base);
                    }
                }
            }
            inCalc = false;
        }

        super.calculateCardDamage(m);
    }

    @Override
    public void resetAttributes() {
        super.resetAttributes();

        for (LocalVarInfo var : cardVariables.values()) {
            var.value = var.base;
        }
    }

    private static class QuickDynamicVariable extends DynamicVariable {
        final String localKey, key;

        private AbstractCoinforgedCard current = null;

        public QuickDynamicVariable(String key) {
            this.localKey = key;
            this.key = makeID(key);
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void setIsModified(AbstractCard c, boolean v) {
            if (c instanceof AbstractCoinforgedCard) {
                LocalVarInfo var = ((AbstractCoinforgedCard) c).getCustomVar(localKey);
                if (var != null)
                    var.forceModified = v;
            }
        }

        @Override
        public boolean isModified(AbstractCard c) {
            return c instanceof AbstractCoinforgedCard && (current = (AbstractCoinforgedCard) c).isCustomVarModified(localKey);
        }

        @Override
        public int value(AbstractCard c) {
            return c instanceof AbstractCoinforgedCard ? ((AbstractCoinforgedCard) c).customVar(localKey) : 0;
        }

        @Override
        public int baseValue(AbstractCard c) {
            return c instanceof AbstractCoinforgedCard ? ((AbstractCoinforgedCard) c).customVarBase(localKey) : 0;
        }

        @Override
        public boolean upgraded(AbstractCard c) {
            return c instanceof AbstractCoinforgedCard && ((AbstractCoinforgedCard) c).customVarUpgraded(localKey);
        }

        public Color getNormalColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.CREAM_COLOR;

            return var.normalColor;
        }

        public Color getUpgradedColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.GREEN_TEXT_COLOR;

            return var.upgradedColor;
        }

        public Color getIncreasedValueColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.GREEN_TEXT_COLOR;

            return var.increasedColor;
        }

        public Color getDecreasedValueColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.RED_TEXT_COLOR;

            return var.decreasedColor;
        }
    }

    protected static class LocalVarInfo {
        int base, value, upgrade;
        int[] aoeValue = null;
        boolean upgraded = false;
        boolean forceModified = false;
        Color normalColor = Settings.CREAM_COLOR;
        Color upgradedColor = Settings.GREEN_TEXT_COLOR;
        Color increasedColor = Settings.GREEN_TEXT_COLOR;
        Color decreasedColor = Settings.RED_TEXT_COLOR;

        BiFunction<AbstractMonster, Integer, Integer> calculation = LocalVarInfo::noCalc;

        public LocalVarInfo(int base, int upgrade) {
            this.base = this.value = base;
            this.upgrade = upgrade;
        }

        private static int noCalc(AbstractMonster m, int base) {
            return base;
        }

        public boolean isModified() {
            return forceModified || base != value;
        }
    }
}