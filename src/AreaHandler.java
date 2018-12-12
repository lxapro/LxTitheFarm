import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class AreaHandler {

    Area startArea = new Area(new Position(1778, 3587, 0), new Position(1788, 3595, 0));

    Position farmEntrySpot;
    private Position rightEdgeCentre;
    private Position startPosition;

    void setFarmEntrySpot(Position p){
        farmEntrySpot = p;
    }

    void setRightEdgeCentrePosition(){
        rightEdgeCentre = new Position(farmEntrySpot.getX() - 3, farmEntrySpot.getY() + 6, farmEntrySpot.getZ());
    }

    void setStartPosition(){
        startPosition = new Position(rightEdgeCentre.getX() - 5, rightEdgeCentre.getY() + 3, rightEdgeCentre.getZ());
    }

}
