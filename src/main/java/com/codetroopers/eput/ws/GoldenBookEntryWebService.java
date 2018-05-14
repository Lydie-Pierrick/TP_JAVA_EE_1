
package com.codetroopers.eput.ws;


import com.codetroopers.eput.domain.entities.GoldenBookEntry;
import com.codetroopers.eput.services.GoldenBookEntryService;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Sample REST WebService, it will be under "ws" app path
 */
//tag::class[]
@ApplicationPath("ws") // <1>
@Path("/entries") // <2>
@Produces(MediaType.APPLICATION_JSON) // <3>
public class GoldenBookEntryWebService extends Application {

    @Inject
    GoldenBookEntryService goldenbookService;

    @GET // <4>
    public List<GoldenBookEntry> goldenbook(){
        return goldenbookService.loadGoldenBookEntries();
    }
}
//end::class[]
