import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;

@ScriptManifest(version = 0.1, author = "Alex", info = "A tithe farm bot", name = "LxTitheFarm", logo="")
public class LxTitheFarm extends Script {

    private AreaHandler areas;
    private boolean isUsingLunars;

    @Override
    public void onStart(){
        areas = new AreaHandler(this);
        if(areas.startArea.contains(myPosition())){
            if(tryOpenSeedTable()){
                if(tryGetSeeds()){
                    if(tryEnterFarm()){
                        createFarmArea();

                    }
                }
            } else{
                log("Failed to open seed table");
                stop(false);
            }
        } else{
            log("Please start script within Tithe Farm lobby.");
            stop(false);
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        return 0;
    }


    @Override
    public void onExit(){

    }

    @Override
    public void onPaint(Graphics2D g){

    }

    private boolean tryOpenSeedTable(){
        //Tries to open the seed table in tithe farm lobby, waits 5 seconds and then returns whether a dialogue screen
        // is now open, seed table id = 27430
        if(getInventory().contains(getSeedID(getFarmingLevel()))){
            return true;
        }
        getObjects().closest(27430).interact();
        new ConditionalSleep(5000){
            public boolean condition() throws InterruptedException {
                return getDialogues().isPendingOption();
            }
        }.sleep();
        return getDialogues().isPendingOption();
    }

    private boolean tryGetSeeds(){
        if(getDialogues().isPendingOption()) {
            getDialogues().selectOption(getSeedDialogueOption(getFarmingLevel()));
        }
        new ConditionalSleep(5000){
            public boolean condition() throws InterruptedException {
                return getInventory().contains(getSeedID(getFarmingLevel()));
            }
        }.sleep();
        return getInventory().contains(getSeedID(getFarmingLevel()));
    }

    private boolean tryEnterFarm(){
        getObjects().closest(27445).interact();
        Position p = myPosition();
        new ConditionalSleep(5000){
            public boolean condition() throws InterruptedException {
                return !areas.startArea.contains(myPosition());
            }
        }.sleep();
        log(myPosition());
        return true;
    }

    private void createFarmArea(){
        areas.setFarmEntrySpot(myPosition());
        areas.setRightEdgeCentrePosition();
        areas.setStartPosition();
        areas.createPatchAreas();
        if(moveToStart()){
            log("destination reached");
        } else{
            log("failed to reach destination");
        }
    }

    private boolean moveToStart(){
        log("walking to start position");
        return getWalking().walk(new Area(new Position(areas.startPosition.getX() -1, areas.startPosition.getY(), areas.startPosition.getZ()), new Position(areas.startPosition.getX(), areas.startPosition.getY() + 1, areas.startPosition.getZ())));
    }

    void logAreas(){
        for(int i = 0; i < areas.patchPairs.length; i++){
            log(areas.patchPairs[i].startPos);
            if(i != 2) {
                log(areas.patchPairs[i].linkedPatches[0].patchArea.getPositions().toString());
            } else {
                log(areas.patchPairs[i].linkedPatches[1].patchArea.getPositions().toString());
            }
        }
    }

    private int getSeedDialogueOption(int farmingLevel){
        if(farmingLevel >= 74){
            return 3;
        } else if(farmingLevel >= 54){
            return 2;
        }
        return 1;
    }

    private int getSeedID(int farmingLevel){
        if(farmingLevel >= 74){
            return 13425;
        } else if(farmingLevel >= 54){
            return 13424;
        }
        return 13423;
    }

    private int getFarmingLevel(){
        return getSkills().getDynamic(Skill.FARMING);
    }
}
