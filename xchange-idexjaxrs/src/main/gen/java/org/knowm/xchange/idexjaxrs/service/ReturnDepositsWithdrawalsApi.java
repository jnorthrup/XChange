package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.DepositsWithdrawalsReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnDepositsWithdrawalsResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnDepositsWithdrawals")
@Api(description = "the returnDepositsWithdrawals API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnDepositsWithdrawalsApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns your deposit and withdrawal history within a range, specified by the \"start\" and \"end\" properties of the JSON input, both of which must be UNIX timestamps. Withdrawals can be marked as \"PENDING\" if they are queued for dispatch, \"PROCESSING\" if the transaction has been dispatched, and \"COMPLETE\" if the transaction has been mined.", notes = "", response = ReturnDepositsWithdrawalsResponse.class, tags={ "account" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnDepositsWithdrawalsResponse.class) })
    public Response fundingHistory(@Valid DepositsWithdrawalsReq depositsWithdrawalsReq) {
        return Response.ok().entity("magic!").build();
    }
}
