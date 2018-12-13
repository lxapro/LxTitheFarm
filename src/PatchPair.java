import org.osbot.rs07.api.map.Position;

public class PatchPair {

    //A linked pair of adjacent 4 patches

    PatchGroup[] linkedPatches; //0 patch on left side, 1 on right side, 0 will be null for the single patch

    PatchPair(int patchID, Position startPosition){
        //Start position is the left middle tile of patch 1
        linkedPatches = new PatchGroup[2];
        linkedPatches[1] = new PatchGroup(startPosition);
        if(patchID == 2){
            linkedPatches[0] = null;
        } else{
            linkedPatches[0] = new PatchGroup(new Position(startPosition.getX() -5, startPosition.getY(), startPosition.getZ()));
        }
/*        linkedPatches[0] = leftPatch;
        linkedPatches[1] = rightPatch;*/
    }

}
