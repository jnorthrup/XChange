package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.CancelReq;
import org.knowm.xchange.idexjaxrs.dto.CancelResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/cancel")
@Api(description = "the cancel API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class CancelApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Cancels an order associated with the address. JSON input must include the following properties", notes = "", response = CancelResponse.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "To derive the signature for this API call, hash the following parameters in this orderorderHashnonceSalt and sign the hash as usual to prepare your payloadSample output:{ success: 1 }", response = CancelResponse.class) })
    public Response cancel(@Valid CancelReq cancelReq) {
        return Response.ok().entity("magic!").build();
    }
}
