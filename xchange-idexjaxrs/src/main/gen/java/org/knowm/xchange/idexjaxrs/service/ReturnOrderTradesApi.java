package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.OrderTradesReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnOrderTradesResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnOrderTrades")
@Api(description = "the returnOrderTrades API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnOrderTradesApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns all trades involving a given order hash, specified by the orderHash property of the JSON input.", notes = "", response = ReturnOrderTradesResponse.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sample output: [ { date: '2017-10-11 21:41:15',amount: '0.3',type: 'buy',total: '1',price: '0.3',uuid: 'e8719a10-aecc-11e7-9535-3b8451fd4699',transactionHash: '0x28b945b586a5929c69337929533e04794d488c2d6e1122b7b915705d0dff8bb6' } ]", response = ReturnOrderTradesResponse.class) })
    public Response orderTrades(@Valid OrderTradesReq orderTradesReq) {
        return Response.ok().entity("magic!").build();
    }
}
