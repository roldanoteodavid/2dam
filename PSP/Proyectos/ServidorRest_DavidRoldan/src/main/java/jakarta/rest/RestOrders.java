package jakarta.rest;

import domain.modelo.Order;
import domain.servicios.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestOrders {

    private final OrderService orderService;

    @Inject
    public RestOrders(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    public List<Order> getAllOrders() {
        return orderService.getAll().get();
    }

    @GET
    @Path("/{id}")
    public Order getOrder(@PathParam("id") int id) {
        return orderService.get(id).get();
    }

    @POST
    public Response addOrder(Order order) {
        orderService.save(order);
        return Response.status(Response.Status.CREATED)
                .entity(order).build();
    }

    @PUT
    public Response updateOrder(Order order, @QueryParam("id") int id) {
        orderService.update(order);
        return Response.status(Response.Status.OK)
                .entity(order).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") int id) {
        orderService.delete(orderService.get(id).get());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
