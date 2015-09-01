package ro.agitman.ing.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by edi on 24.08.15.
 */
// enable async in the servlet
@WebServlet(urlPatterns="/data")
public class IngServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        
    }


}
