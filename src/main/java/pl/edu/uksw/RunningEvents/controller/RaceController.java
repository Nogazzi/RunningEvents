package pl.edu.uksw.RunningEvents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uksw.RunningEvents.model.entity.Race;
import pl.edu.uksw.RunningEvents.model.repositories.RaceJpaRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Nogaz on 10.08.2017.
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/races")
public class RaceController {

    static Logger log = Logger.getLogger(RaceController.class.getName());

    @Autowired
    private RaceJpaRepository raceJpaRepository;


    @GetMapping(value = "/")
    public List<Race> findAll() {
        return raceJpaRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Race findById(@PathVariable final Long id) {
        return raceJpaRepository.findById(id);
    }

    @GetMapping(value = "/maxdistance")
    public List<Race> getShorterRaces(@RequestParam("maxDistance") int maxDistance) {
        return raceJpaRepository.findByDistanceLessThan(maxDistance);
    }

    @RequestMapping(value = "/load")
    public Race load(@RequestParam("name") String name,
                     @RequestParam("date") String date,
                     @RequestParam("organizer") String organizer,
                     @RequestParam("localization") String localization,
                     @RequestParam("distance") int distance) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateParsed = format.parse(date);
        Race race = new Race(name, dateParsed, organizer, localization, distance);
        raceJpaRepository.save(race);
        return null;//raceJpaRepository.findByName(race.getName()).get(0);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeEvent(@PathVariable final Long id){
        final Race toRemove = raceJpaRepository.findById(id);
        log.info("Event to delete: " + toRemove.toString());
        if (toRemove != null) {
            raceJpaRepository.delete(toRemove);
            log.info("Deleted event: " + toRemove.toString());
            return ResponseEntity.ok("Event " + toRemove.toString() + " deleted succefully.");
        }

        return ResponseEntity.ok("Item to delete was not found!");
    }

    @RequestMapping(value = "/registerevent", method = RequestMethod.POST)
    public ResponseEntity<String> registerNewEvent(@RequestBody Race input) {
        log.info("Sent race details" + input);
        Date data = new Date(input.getDate().getTime());
        log.info("Formated date: " + data);
        raceJpaRepository.save(input);
        return ResponseEntity.ok("Event " + input.toString() + " added succefully.");
    }
}
