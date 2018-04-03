package org.knowm.xchange.idexjaxrs.service;

import org.knowm.xchange.idexjaxrs.dto.Volume24Response;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/return24Volume")
@Api(description = "the return24Volume API")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class Return24VolumeApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns the 24-hour volume for all markets, plus totals for primary currencies. ", notes = "", response = Volume24Response.class, tags={ "market" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "This function takes no JSON arguments{ ETH_REP: { ETH: '1.3429046745', REP: '105.29046745' },ETH_DVIP: { ETH: '4', DVIP: '4' },totalETH: '5.3429046745' }", response = Volume24Response.class) })
    public Response volume24() {
        return Response.ok().entity("magic!").build();
    }
}
