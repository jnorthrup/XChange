package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.WithdrawReq;
import org.knowm.xchange.idexjaxrs.dto.WithdrawResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/withdraw")
@Api(description = "the withdraw API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class WithdrawApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Withdraws funds associated with the address. You cannot withdraw funds that are tied up in open orders. JSON payload must include the following properties", notes = "", response = WithdrawResponse.class, tags={ "account" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "To derive the signature for this API call, hash the following parameters in this order contract addresstokenamountaddressnonceSalt the hash as described earlier and sign it to produce your signature triplet.Useful response upon withdrawal success is in the works, for now simply test that there is no error propertyin the result object to confirm your withdrawal has succeeded.", response = WithdrawResponse.class) })
    public Response withdraw(@Valid WithdrawReq withdrawReq) {
        return Response.ok().entity("magic!").build();
    }
}
