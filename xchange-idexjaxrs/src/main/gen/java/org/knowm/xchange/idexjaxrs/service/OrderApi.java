package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.InlineResponse200;
import org.knowm.xchange.idexjaxrs.dto.OrderReq;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/order")
@Api(description = "the order API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class OrderApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Limit Order", notes = "", response = InlineResponse200.class, tags={ "trade" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = InlineResponse200.class) })
    public Response order(@Valid OrderReq orderReq) {
        return Response.ok().entity("magic!").build();
    }
}
