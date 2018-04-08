package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.NextNonceReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnNextNonceResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnNextNonce")
@Api(description = "the returnNextNonce API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnNextNonceApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns the lowest nonce that you can use from the given address in one of the trade functions (see below)", notes = "", response = ReturnNextNonceResponse.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sample output: { nonce: 2650 }", response = ReturnNextNonceResponse.class) })
    public Response nextNonce(@Valid NextNonceReq nextNonceReq) {
        return Response.ok().entity("magic!").build();
    }
}
