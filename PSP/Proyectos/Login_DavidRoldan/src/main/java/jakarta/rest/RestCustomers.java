package jakarta.rest;


import domain.modelo.Customer;
import domain.servicios.CustomerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(Constantes.CUSTOMERSPATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCustomers {

    private final CustomerService customerService;

    @Inject
    public RestCustomers(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GET
    @RolesAllowed({Constantes.ROLE_ADMIN, Constantes.ROLE_USER})
    public List<Customer> getAllCustomers() {
        return customerService.getAll().get();
    }

    @GET
    @RolesAllowed({Constantes.ROLE_ADMIN, Constantes.ROLE_USER})
    @Path(Constantes.IDPATH)
    public Customer getCustomer(@PathParam(Constantes.ID) int id) {
        return customerService.get(id).get();
    }


    @POST
    @RolesAllowed(Constantes.ROLE_ADMIN)
    public Response addCustomer(Customer customer) {
        customerService.save(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @PUT
    @RolesAllowed(Constantes.ROLE_ADMIN)
    public Response updateCustomer(Customer customer) {
        customerService.update(customer);
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @DELETE
    @RolesAllowed(Constantes.ROLE_ADMIN)
    @Path(Constantes.IDPATH)
    public Response delCustomer(@PathParam(Constantes.ID) int id) {
        customerService.delete(customerService.get(id).get());
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
