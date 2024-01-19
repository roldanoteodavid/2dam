package jakarta.servlets;

import jakarta.common.Constantes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = Constantes.SERVLET, value = Constantes.SERVLETROOT)
public class MiPrimerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(Constantes.CONTADOR) == null) {
            req.getSession().setAttribute(Constantes.CONTADOR, 0);
        }
        if (req.getSession().getAttribute(Constantes.NUMBERTOGUESS) == null) {
            req.getSession().setAttribute(Constantes.NUMBERTOGUESS, new Random().nextInt(10) + 1);
        }
        if (req.getSession().getAttribute(Constantes.GUESS) == null) {
            req.getSession().setAttribute(Constantes.GUESS, 0);
        }

        int contador = (int) req.getSession().getAttribute(Constantes.CONTADOR);
        int numberToGuess = (int) req.getSession().getAttribute(Constantes.NUMBERTOGUESS);

        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(Constantes.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        boolean fin = false;
        if (req.getParameter(Constantes.GUESS) == null || req.getParameter(Constantes.GUESS).isEmpty()) {
            context.setVariable(Constantes.MESSAGE, Constantes.INTRODUZCA_UN_NUMERO);
            context.setVariable(Constantes.CONTADOR, contador);
        } else if (req.getSession().getAttribute(Constantes.GUESS).equals(Integer.parseInt(req.getParameter(Constantes.GUESS)))) {
            context.setVariable(Constantes.MESSAGE, Constantes.ERES_IDIOTA_HAS_REPETIDO_NUMERO);
            context.setVariable(Constantes.CONTADOR, contador);
        } else {
            if (contador < 4) {
                int userGuess;
                try {
                    userGuess = Integer.parseInt(req.getParameter(Constantes.GUESS));
                    String message;
                    if (userGuess == numberToGuess) {
                        message = Constantes.FELICIDADES_ADIVINASTE_EL_NUMERO + numberToGuess + Constantes.INTRODUZCA_UN_NUMERO_SI_DESEA_VOLVER_A_EMPEZAR;
                        fin = true;
                    } else {
                        if (userGuess < numberToGuess) {
                            message = Constantes.EL_NUMERO_ES_MAYOR_INTENTA_NUEVAMENTE;
                            req.getSession().setAttribute(Constantes.GUESS, userGuess);
                        } else {
                            message = Constantes.EL_NUMERO_ES_MENOR_INTENTA_NUEVAMENTE;
                            req.getSession().setAttribute(Constantes.GUESS, userGuess);
                        }
                    }
                    contador++;
                    req.getSession().setAttribute(Constantes.CONTADOR, contador);
                    context.setVariable(Constantes.CONTADOR, contador);
                    context.setVariable(Constantes.MESSAGE, message);
                } catch (NumberFormatException e) {
                    context.setVariable(Constantes.ERROR, Constantes.NUMERO_NO_VALIDO);
                }
            } else {
                context.setVariable(Constantes.MESSAGE, Constantes.HAS_PERDIDO_INTRODUZCA_UN_NUMERO_SI_DESEA_VOLVER_A_EMPEZAR);
                fin = true;
                contador++;
                context.setVariable(Constantes.CONTADOR, contador);
            }
        }

        templateEngine.process(Constantes.GUESS, context, resp.getWriter());
        if (fin) {
            contador = 0;
            req.getSession().setAttribute(Constantes.NUMBERTOGUESS, new Random().nextInt(10) + 1);
            req.getSession().setAttribute(Constantes.CONTADOR, contador);
            req.getSession().setAttribute(Constantes.GUESS ,0);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
