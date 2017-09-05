package pl.edu.uksw.RunningEvents.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nogaz on 03.08.2017.
 */
@Entity
@Table(name = "race")
public class Race implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "organizer")
    private String organizer;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "localization")
    private String localization;

    @Column(name = "distance")
    private int distance;

    @Column(name = "description")
    private String description;

    /*@Column(name = "waves")
    @ElementCollection(targetClass=Integer.class)
    private Set<Wave> waves;*/

    public Race() {
        //for JPA
    }

    /*public Race(String name, Date date, String localization, int distance, String description, Set<Wave> waves) {
        this.name = name;
        this.date = date;
        this.localization = localization;
        this.distance = distance;
        this.description = description;
        this.waves = waves;
    }*/

    public Race(String name, Date date, String organizer, String localization, int distance) {
        this.name = name;
        this.date = date;
        this.organizer = organizer;
        this.localization = localization;
        this.distance = distance;
        this.description = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Set<Wave> getWaves() {
        return waves;
    }

    public void setWaves(Set<Wave> waves) {
        this.waves = waves;
    }*/

    @Override
    public String toString() {
        return "Event[name: " + name + ", date: " + date + ", localization: " + localization + ", distance: " + distance + "]";
    }
}
