package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.TradeHistoryItem;
import org.knowm.xchange.idexjaxrs.dto.TradeHistoryReq;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/returnTradeHistory")
@Api(description = "the returnTradeHistory API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ReturnTradeHistoryApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "trade history", notes = "", response = TradeHistoryItem.class, responseContainer = "List", tags={ "market" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = TradeHistoryItem.class, responseContainer = "List") })
    public Response tradeHistory(@Valid TradeHistoryReq tradeHistoryReq) {
        return Response.ok().entity("magic!").build();
    }
}
