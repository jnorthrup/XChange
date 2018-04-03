package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.TradeReq;
import org.knowm.xchange.idexjaxrs.dto.TradeResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/trade")
@Api(description = "the trade API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class TradeApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Making a trade on IDEX actually involves signing a message for each order you wish to fill across and passing in an array of trades. For trades that fill a single order, the usual array with 1 object, or the object alone. The benefit of passing in multiple objects to fill across is that your action is atomic. All trades either succeed or none succeed.", notes = "", response = TradeResponse.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Properties of each trade object in the trade you submit:To derive the hash you must sign for each order you are filling across, you must hash the following values in this orderorderHashamountaddressnonceApply the salt and hash the result as usual, then sign your salted hash.NOTE: Currently, all orders being filled in a trade must be for the same tokenBuy/tokenSell pair, and must all be signed from the same addressSample output:[ { amount: Ã¢â‚¬Ëœ0.07Ã¢â‚¬â„¢,date: Ã¢â‚¬Ëœ2017-10-13 16:25:36Ã¢â‚¬â„¢,total: Ã¢â‚¬Ëœ0.01Ã¢â‚¬â„¢,market: Ã¢â‚¬ËœETH_DVIPÃ¢â‚¬â„¢,type: Ã¢â‚¬ËœbuyÃ¢â‚¬â„¢,price: Ã¢â‚¬Ëœ7Ã¢â‚¬â„¢,orderHash: Ã¢â‚¬Ëœ0xcfe4018c59e50e0e1964c979e6213ce5eb8c751cbc98a44251eb48a0985adc52Ã¢â‚¬â„¢,uuid: Ã¢â‚¬Ëœ250d51a0-b033-11e7-9984-a9ab79bb8f35Ã¢â‚¬â„¢ } ]", response = TradeResponse.class) })
    public Response trade(@Valid TradeReq tradeReq) {
        return Response.ok().entity("magic!").build();
    }
}
