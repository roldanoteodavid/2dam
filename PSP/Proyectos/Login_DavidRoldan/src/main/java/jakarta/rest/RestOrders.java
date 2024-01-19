package jakarta.rest;

import domain.modelo.Order;
import domain.servicios.OrderService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(Constantes.ORDERSPATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestOrders {

    private final OrderService orderService;

    @Inject
    public RestOrders(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @RolesAllowed({Constantes.ROLE_ADMIN, Constantes.ROLE_USER})
    public List<Order> getAllOrders() {
        return orderService.getAll().get();
    }

    @GET
    @Path(Constantes.IDPATH)
    @RolesAllowed({Constantes.ROLE_ADMIN, Constantes.ROLE_USER})
    public Order getOrder(@PathParam(Constantes.ID) int id) {
        return orderService.get(id).get();
    }

    @POST
    @RolesAllowed(Constantes.ROLE_ADMIN)
    public Response addOrder(Order order) {
        orderService.save(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @RolesAllowed(Constantes.ROLE_ADMIN)
    public Response updateOrder(Order order) {
        orderService.update(order);
        return Response.status(Response.Status.OK).entity(order).build();
    }

    @DELETE
    @RolesAllowed(Constantes.ROLE_ADMIN)
    @Path(Constantes.IDPATH)
    public Response deleteOrder(@PathParam(Constantes.ID) int id) {
        orderService.delete(orderService.get(id).get());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
