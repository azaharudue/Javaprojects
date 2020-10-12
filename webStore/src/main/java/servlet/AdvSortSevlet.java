package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.stores.AdvStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public final class AdvSortSevlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUser = request.getParameter("currentUser");
        String sortBy = request.getParameter("sortBy");

        List<Adv> advList = null;
        try (AdvStore advStore = new AdvStore()) {
            advList = advStore.getSortedAdvList(sortBy);
            advStore.complete();
        }

        request.setAttribute("advList", advList);
        request.setAttribute("currentUser", currentUser);

        request.getRequestDispatcher("/shop.ftl").forward(request, response);
    }


}