package CoinforgedPackage.ui;

import static CoinforgedPackage.CoinforgedMain.makeID;
import static CoinforgedPackage.CoinforgedMain.imagePath;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

import CoinforgedPackage.util.TextureLoader;

public class ReturnTextIcon extends AbstractCustomIcon {
    public static final String ID = makeID("return");
    private static ReturnTextIcon singleton;
    private static final Texture iconTex = TextureLoader.getTexture(imagePath("/ui/CardTextReturnIcon.png"));

    public ReturnTextIcon() {
        super(ID, iconTex);
    }

    public static ReturnTextIcon get() {
        if (singleton == null) {
            singleton = new ReturnTextIcon();
        }
        return singleton;
    }
}