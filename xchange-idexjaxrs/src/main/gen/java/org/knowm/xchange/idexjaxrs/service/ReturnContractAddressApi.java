package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.ReturnContractAddressResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnContractAddress")
@Api(description = "the returnContractAddress API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnContractAddressApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns the contract address used for depositing, withdrawing, and posting orders", notes = "", response = ReturnContractAddressResponse.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnContractAddressResponse.class) })
    public Response contractAddress() {
        return Response.ok().entity("magic!").build();
    }
}
