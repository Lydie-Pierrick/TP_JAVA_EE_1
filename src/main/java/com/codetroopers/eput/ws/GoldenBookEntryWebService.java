
package com.codetroopers.eput.ws;


import com.codetroopers.eput.domain.entities.GoldenBookEntry;
import com.codetroopers.eput.services.GoldenBookEntryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Sample REST WebService, it will be under "ws" app path
 */
//tag::class[]
@ApplicationPath("ws") // <1>
@Path("/goldenbook") // <2>
@Produces(MediaType.APPLICATION_JSON) // <3>
public class GoldenBookEntryWebService extends Application {

    @Inject
    GoldenBookEntryService goldenbookService;

    @GET // <4>
    public List<GoldenBookEntry> goldenbookList(){
        return goldenbookService.loadGoldenBookEntries();
    }

    @POST // <4>
    public GoldenBookEntry create(
        @QueryParam("title") String title,
        @QueryParam("body") String body,
        @QueryParam("userId") Long userId){
        GoldenBookEntry goldenBookEntry = new GoldenBookEntry(userId, title, body);
        return goldenbookService.insertNewGoldenBookEntry(goldenBookEntry);
    }

    @PUT
    public Boolean putRating(
            @QueryParam("entryId") Integer entryId,
            @QueryParam("rating") Integer rating,
            @QueryParam("userId") Long userId){
        return goldenbookService.ratingChange(entryId, rating, userId);
    }

    @DELETE
    public Integer delete(
        @QueryParam("entryId") Integer entryId,
        @QueryParam("userId") Long userId){
        if(goldenbookService.deleteGoldenBookEntry(entryId))
            return 202;
        else
            return 404;
    }
}
//end::class[]
