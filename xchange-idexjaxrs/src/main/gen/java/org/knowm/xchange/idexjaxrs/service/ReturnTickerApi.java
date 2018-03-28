package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.Market;
import org.knowm.xchange.idexjaxrs.dto.ReturnTickerResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnTicker")
@Api(description = "the returnTicker API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnTickerApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "", response = ReturnTickerResponse.class, tags={ "market" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnTickerResponse.class) })
    public Response ticker(@Valid Market market) {
        return Response.ok().entity("magic!").build();
    }
}
