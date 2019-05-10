package com.bank.error.service;

import com.bank.error.model.ResponseModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/error")
public class MoneyTransferService {

    @POST
    @Path("/control")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel isAlive() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setSuccess(true);
        return responseModel;
    }

}
