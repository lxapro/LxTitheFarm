import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class AreaHandler {

    Area startArea = new Area(new Position(1778, 3587, 0), new Position(1788, 3595, 0));

    Position farmEntrySpot;

    void setFarmEntrySpot(Position p){
        farmEntrySpot = p;
    }

}
