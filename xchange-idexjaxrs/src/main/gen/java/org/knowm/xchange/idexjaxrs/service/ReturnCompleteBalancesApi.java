package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.CompleteBalancesReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnCompleteBalancesResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnCompleteBalances")
@Api(description = "the returnCompleteBalances API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnCompleteBalancesApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns your available balances along with the amount you have in open orders for each token, indexed by token symbol.", notes = "", response = ReturnCompleteBalancesResponse.class, tags={ "account" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnCompleteBalancesResponse.class) })
    public Response completeBalances(@Valid CompleteBalancesReq completeBalancesReq) {
        return Response.ok().entity("magic!").build();
    }
}
