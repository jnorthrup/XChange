package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.OrderBookReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnOrderBookResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnOrderBook")
@Api(description = "the returnOrderBook API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnOrderBookApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns the orderbook for a given market, or returns an object of the entire orderbook keyed by\\ market if the market parameter is omitted.", notes = "", response = ReturnOrderBookResponse.class, tags={ "market" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ReturnOrderBookResponse.class) })
    public Response orderBook(@Valid OrderBookReq orderBookReq) {
        return Response.ok().entity("magic!").build();
    }
}
