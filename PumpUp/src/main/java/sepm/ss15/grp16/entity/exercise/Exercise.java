package sepm.ss15.grp16.entity.exercise;

import sepm.ss15.grp16.entity.DTO;
import sepm.ss15.grp16.entity.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 30.04.2015.
 */
public class Exercise implements DTO {

    private Integer id;
    private String name;
    private String description;
    private Double calories;
    private String videolink;
    private List<String> gifLinks = new ArrayList<>();
    private Boolean isDeleted;
    private User user;
    private List<AbsractCategory> categories = new ArrayList<>();


    public Exercise(Integer id, String name, String description, Double calories, String videolink, List<String> gifLinks, Boolean isDeleted, User user, List<AbsractCategory> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.videolink = videolink;
        this.gifLinks = gifLinks;
        this.isDeleted = isDeleted;
        this.user = user;
        this.categories = categories;
    }

    public Exercise(String name, String description, Double calories, String videolink, List<String> gifLinks, Boolean isDeleted, User user, List<AbsractCategory> categories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.videolink = videolink;
        this.gifLinks = gifLinks;
        this.isDeleted = isDeleted;
        this.user = user;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public List<String> getGifLinks() {
        return gifLinks;
    }

    public void setGifLinks(List<String> gifLinks) {
        this.gifLinks = gifLinks;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AbsractCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<AbsractCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;

        Exercise exercise = (Exercise) o;

        if (this.getId() == null) {
            return false;
        }

        if (this.getId() == ((Exercise) o).getId())
            return true;

        return !(getId() != null ? !getId().equals(exercise.getId()) : exercise.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", videolink='" + videolink + '\'' +
                ", gifLinks=" + gifLinks +
                ", isDeleted=" + isDeleted +
                ", user=" + user +
                ", categories=" + categories +
                '}';
    }
}
