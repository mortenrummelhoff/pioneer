package dk.mhr.ihc;

import dk.mhr.ihc.wsdl.cxf.OpenAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mortenrummelhoff on 14/03/16.
 */
@Component
public class IhcService {


    @Autowired
    private OpenAPIService openAPIService;

    public void ping() {
     openAPIService.ping();
    }

    public Integer getApiVersion() {
        return openAPIService.getAPIVersion();
    }

}
