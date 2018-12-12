import org.osbot.rs07.api.map.Area;

public class Patch {

    int patchID; //ID of the patch within its group, from 0 to 3, 0 being the furthest south patch
    Area patchArea;

    Patch(int id, Area a){
        patchID = id;
        patchArea = a;
    }

}
