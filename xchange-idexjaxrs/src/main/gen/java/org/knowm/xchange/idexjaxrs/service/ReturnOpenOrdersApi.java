package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.OpenOrdersReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnOpenOrdersResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnOpenOrders")
@Api(description = "the returnOpenOrders API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnOpenOrdersApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns the open orders for a given market and address", notes = "", response = ReturnOpenOrdersResponse.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnOpenOrdersResponse.class) })
    public Response openOrders(@Valid OpenOrdersReq openOrdersReq) {
        return Response.ok().entity("magic!").build();
    }
}
