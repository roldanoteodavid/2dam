package jakarta.servlets;

import domain.modelo.errores.ApiError;
import domain.servicios.ServicesCredentials;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.rest.Constantes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = Constantes.SERVLET_ACTIVACION, value = Constantes.ACTIVACION)
public class ServletActivacion extends HttpServlet {

    private transient ServicesCredentials servicesCredentials;

    @Inject
    public ServletActivacion(ServicesCredentials servicesCredentials) {
        this.servicesCredentials = servicesCredentials;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter(Constantes.CODIGO);
        Either<ApiError, Integer> activacion = servicesCredentials.activarUsuario(codigo);

        if (activacion.isLeft()){
            response.getWriter().println(activacion.getLeft());
        } else {
            response.getWriter().println(Constantes.USUARIO_ACTIVADO);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //nada
    }
}
