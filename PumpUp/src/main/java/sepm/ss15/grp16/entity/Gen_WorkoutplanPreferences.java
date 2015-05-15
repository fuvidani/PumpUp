package sepm.ss15.grp16.entity;

import sepm.ss15.grp16.entity.Training.Trainingsplan;

import java.util.List;

/**
 * Created by Daniel Fuevesi on 15.05.15.
 *
 */
public class Gen_WorkoutplanPreferences implements DTO {

    private int id;
    private TrainingsCategory goal;
    private List<EquipmentCategory> equipments;

    public Gen_WorkoutplanPreferences(int id, TrainingsCategory goal, List<EquipmentCategory> equipments){
        this.id = id;
        this.goal = goal;
        this.equipments = equipments;
    }

    public TrainingsCategory getGoal() {
        return goal;
    }

    public List<EquipmentCategory> getEquipments() {
        return equipments;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public Boolean getIsDeleted() {
        return null;
    }

    @Override
    public void setIsDeleted(Boolean deleted) {
        // no implementation
    }
}
