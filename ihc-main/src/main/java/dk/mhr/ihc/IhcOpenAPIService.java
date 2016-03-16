package dk.mhr.ihc;

import dk.mhr.ihc.wsdl.cxf.OpenAPIService;
import dk.mhr.ihc.wsdl.cxf.OpenAPIServiceService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.ws.BindingProvider;

/**
 * Created by mortenrummelhoff on 07/03/16.
 */
@Component
public class IhcOpenAPIService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    private OpenAPIService initializeClient() {
        try {

            OpenAPIServiceService serviceService = new OpenAPIServiceService();
            OpenAPIService service = serviceService.getOpenAPIServiceBindingPort();

            BindingProvider bp = (BindingProvider) service;

            //set IHC ip
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://192.168.0.55/ws/OpenAPIService");
            //enable SESSION Cookies
            bp.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);

            //set timeout to 3 min
            bp.getRequestContext().put("com.sun.xml.ws.request.timeout", 3 * 60 * 1000);

            Client client = ClientProxy.getClient(service);

            HTTPConduit conduit = (HTTPConduit) client.getConduit();

            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setAllowChunking(false);

            conduit.setClient(httpClientPolicy);

            return service;

        } catch (Exception e) {
            logger.warn("Unable to initialize OpenAPIService Stub", e);
        }

        return null;
    }
}
