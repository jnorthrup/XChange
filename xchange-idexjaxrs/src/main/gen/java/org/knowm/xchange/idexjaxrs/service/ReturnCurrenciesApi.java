package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.ReturnCurrenciesResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnCurrencies")
@Api(description = "the returnCurrencies API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnCurrenciesApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "", response = ReturnCurrenciesResponse.class, tags={ "market" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "null", response = ReturnCurrenciesResponse.class) })
    public Response currencies() {
        return Response.ok().entity("magic!").build();
    }
}
