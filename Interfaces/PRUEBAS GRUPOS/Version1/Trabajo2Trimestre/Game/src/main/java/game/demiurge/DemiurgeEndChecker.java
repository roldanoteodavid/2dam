package game.demiurge;

import game.conditions.Condition;
import game.conditions.SimpleCondition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DemiurgeEndChecker implements Serializable {

    ArrayList<Condition> conditions;

    public DemiurgeEndChecker(){
        conditions = new ArrayList<>();
        conditions.add(new SimpleCondition());
    }

    public void addCondition(Condition condition){ conditions.add(condition); }

    public boolean check(){
        for(Condition condition: conditions) {
            if (!condition.check())
                return false;
        }
        return true;
    }

    public List<Condition> getConditions() {
        return conditions;
    }
}
