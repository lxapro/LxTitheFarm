import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class AreaHandler {

    Area startArea = new Area(new Position(1778, 3587, 0), new Position(1788, 3595, 0));

    Position farmEntrySpot;
    private Position rightEdgeCentre;
    private Position startPosition;
    PatchPair[] patchPairs;

    void setFarmEntrySpot(Position p){
        farmEntrySpot = p;
    }

    void setRightEdgeCentrePosition(){
        rightEdgeCentre = new Position(farmEntrySpot.getX() - 3, farmEntrySpot.getY() + 6, farmEntrySpot.getZ());
    }

    void setStartPosition(){
        startPosition = new Position(rightEdgeCentre.getX() - 5, rightEdgeCentre.getY() + 3, rightEdgeCentre.getZ());
    }

    void createPatchAreas(){
        patchPairs = new PatchPair[3];
        patchPairs[0] = new PatchPair(0, new Position(startPosition.getX() + 1, startPosition.getY(), startPosition.getZ()));
        patchPairs[1] = new PatchPair(1, new Position(startPosition.getX() + 1, startPosition.getY() + 17, startPosition.getZ()));
    }

}
