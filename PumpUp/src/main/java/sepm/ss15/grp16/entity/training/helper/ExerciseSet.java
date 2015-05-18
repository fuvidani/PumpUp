package sepm.ss15.grp16.entity.training.helper;

import sepm.ss15.grp16.entity.Exercise;
import sepm.ss15.grp16.entity.User;

/**
 * Author: Lukas
 * Date: 08.05.2015
 */
public class ExerciseSet implements DTOHelper {

	private Integer id;
	private Integer repeat;
	private Integer order_nr;
	private Boolean isDeleted = false;

	private SetType type;

	private User user;

	private Exercise exercise;

	public ExerciseSet() {
	}

	public ExerciseSet(Integer id, Exercise exercise, User user, Integer repeat, SetType type, Integer order_nr, Boolean isDeleted) {
		this.id = id;
		this.exercise = exercise;
		this.user = user;
		this.repeat = repeat;
		this.order_nr = order_nr;
		this.isDeleted = isDeleted;
		this.type = type;
	}

	public ExerciseSet(Exercise exercise, User user, Integer repeat, SetType type, Integer order_nr, Boolean isDeleted) {
		this.id = null;
		this.exercise = exercise;
		this.user = user;
		this.repeat = repeat;
		this.order_nr = order_nr;
		this.isDeleted = isDeleted;
		this.type = type;
	}

	public ExerciseSet(ExerciseSet exerciseSet) {
		this.id = exerciseSet.id;
		this.exercise = exerciseSet.exercise;
		this.user = exerciseSet.user;
		this.repeat = exerciseSet.repeat;
		this.order_nr = exerciseSet.order_nr;
		this.type = exerciseSet.type;
		this.isDeleted = exerciseSet.isDeleted;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRepeat() {
		return repeat;
	}

	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	public SetType getType() {
		return type;
	}

	public void setType(SetType type) {
		this.type = type;
	}

	public Integer getOrder_nr() {
		return order_nr;
	}

	public void setOrder_nr(Integer order_nr) {
		this.order_nr = order_nr;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	@Override
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ExerciseSet)) return false;

		ExerciseSet set = (ExerciseSet) o;

		return !(id != null ? !id.equals(set.id) : set.id != null) &&
				!(repeat != null ? !repeat.equals(set.repeat) : set.repeat != null) &&
				!(order_nr != null ? !order_nr.equals(set.order_nr) : set.order_nr != null) &&
				!(isDeleted != null ? !isDeleted.equals(set.isDeleted) : set.isDeleted != null) &&
				!(type != null ? !type.equals(set.type) : set.type != null) &&
				!(user != null ? !user.equals(set.user) : set.user != null) &&
				!(exercise != null ? !exercise.equals(set.exercise) : set.exercise != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
		result = 31 * result + (order_nr != null ? order_nr.hashCode() : 0);
		result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ExerciseSet{" +
				"id=" + id +
				", repeat=" + repeat +
				", order_nr=" + order_nr +
				", isDeleted=" + isDeleted +
				", type=" + type +
				", user=" + user +
				", exercise=" + exercise +
				'}';
	}

	public static class SetType {
		public static final String REPEAT = "repeat";
		public static final String TIME = "time";

		private String type;

		public SetType(String type) {
			this.type = type;
		}

		public SetType(SetType setType) {
			this.type = setType.type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof SetType)) return false;

			SetType setType = (SetType) o;

			return !(type != null ? !type.equals(setType.type) : setType.type != null);

		}

		@Override
		public int hashCode() {
			return type != null ? type.hashCode() : 0;
		}

		@Override
		public String toString() {
			return type;
		}

		public static SetType getSetType(String type){
			SetType setType = null;

			if(type.equals(REPEAT)){
				setType = new SetType(REPEAT);
			}

			if(type.equals(TIME)){
				setType = new SetType(TIME);
			}

			return setType;
		}
	}
}