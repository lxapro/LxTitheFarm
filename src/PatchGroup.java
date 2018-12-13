import javafx.geometry.Pos;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class PatchGroup {

    //A single strip of 4 patches
    Patch[] patches;
    Area patchArea;


    PatchGroup(Position sWCorner){
        //sWCorner is the bottom left corner of the interactive dirt area, ignoring the 1x3 border on either end
        Position nECorner = new Position(sWCorner.getX() + 2, sWCorner.getY() + 11, sWCorner.getZ());
        patchArea = new Area(sWCorner, nECorner);
        patches = new Patch[4];
        for(int i = 0; i < patches.length; i++){
            Position bttmLeftCorner = new Position(sWCorner.getX(), sWCorner.getY() + (3 * i), sWCorner.getZ());
            Position topRightCorner = new Position(nECorner.getX(), nECorner.getY() - 8 + (3 * i), nECorner.getZ());
            patches[i] = new Patch(i, new Area(bttmLeftCorner, topRightCorner));
        }
    }

}
