package dk.mhr.pioneer.controller;

import dk.mhr.pioneer.DTO.VolumeString;
import dk.mhr.pioneer.service.TelnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mortenrummelhoff on 19/02/16.
 */
@RestController
@RequestMapping("/api/pioneer")
@CrossOrigin("*")
public class PioneerController {

    @Autowired
    private TelnetService telnetService;

    @RequestMapping(path = "/power", name = "power", method = RequestMethod.GET)
    public Boolean isPioneerPoweredOn() {
        return telnetService.isPoweredOn();
    }

    @RequestMapping(path = "/power", method = RequestMethod.PUT)
    public void setPioneerPower(@RequestParam("on") boolean on) {
        telnetService.setPower(on);
    }

    @RequestMapping(path = "/mode", method = RequestMethod.PUT)
    public void setMode(@RequestParam("mode") TelnetService.MODE mode) {
        telnetService.setMode(mode);
    }

    @RequestMapping(path = "/mode", method = RequestMethod.GET)
    public TelnetService.MODE getMode() {
        return telnetService.getMode();
    }

    @RequestMapping(path = "/volume", method = RequestMethod.GET)
    public VolumeString getVolume() {
        return new VolumeString(telnetService.getVolume().trim().substring(3));
    }

    @RequestMapping(path = "/volume", method = RequestMethod.PUT)
    public void setVolume(@RequestParam("vol") int vol) {
        telnetService.setVolume(vol);
    }

}
