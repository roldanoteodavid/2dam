package jakarta.rest;


import domain.modelo.Customer;
import domain.servicios.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCustomers {

    private final CustomerService customerService;


    @Inject
    public RestCustomers(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GET
    public List<Customer> getAllCustomers() {
        return customerService.getAll().get();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        return customerService.get(id).get();
    }


    @POST
    public Response addCustomer(Customer customer) {
        customerService.save(customer);
        return Response.status(Response.Status.CREATED)
                .entity(customer).build();
    }

    @PUT
    public Response updateCustomer(Customer customer, @QueryParam("id") int id) {
        customerService.update(customer);
        return Response.status(Response.Status.OK)
                .entity(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delCustomer(@PathParam("id") int id) {
        customerService.delete(customerService.get(id).get(), true);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
