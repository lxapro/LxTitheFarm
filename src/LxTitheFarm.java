import org.osbot.rs07.api.Skills;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;

public class LxTitheFarm extends Script {

    private AreaHandler areas;

    @Override
    public void onStart(){
        areas = new AreaHandler();
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
        getObjects().closest(27430).interact();
        new ConditionalSleep(5000){
            public boolean condition() throws InterruptedException {
                return getDialogues().inDialogue();
            }
        }.sleep();
        return getDialogues().inDialogue();
    }

    private boolean tryGetSeeds(){
        getDialogues().selectOption(getSeedDialogueOption(getFarmingLevel()));
        new ConditionalSleep(5000){
            public boolean condition() throws InterruptedException {
                return getInventory().contains(getSeedID(getFarmingLevel()));
            }
        }.sleep();
        return getInventory().contains(getSeedID(getFarmingLevel()));
    }

    private boolean tryEnterFarm(){
        return false;
    }

    private void createFarmArea(){

    }

    private int getSeedDialogueOption(int farmingLevel){
        //TODO: Add IDs for seeds
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
