package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.BalancesReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnBalancesResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnBalances")
@Api(description = "the returnBalances API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnBalancesApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Returns your available balances (total deposited minus amount in open orders) indexed by token symbol.", response = ReturnBalancesResponse.class, tags={ "account" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnBalancesResponse.class) })
    public Response balances(@Valid BalancesReq balancesReq) {
        return Response.ok().entity("magic!").build();
    }
}
