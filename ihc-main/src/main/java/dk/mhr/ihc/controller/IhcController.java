package dk.mhr.ihc.controller;

import dk.mhr.ihc.wsdl.cxf.OpenAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.rmi.RemoteException;

/**
 * Created by mortenrummelhoff on 08/03/16.
 */
@RestController
@RequestMapping("/api/ihc")
@CrossOrigin("*")
public class IhcController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OpenAPIService ihcStub;

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public Integer ping() {
        Integer version = null;
        try {
            version = ihcStub.getAPIVersion();
            //ihcStub.getFWVersion();
            //logger.info("Ping called..: {}", ihcStub.getFWVersion());
        } catch (Exception e) {
            logger.error("Ex", e);
        }
        return version;
    }

}
