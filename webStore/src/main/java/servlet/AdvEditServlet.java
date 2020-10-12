package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.stores.AdvStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdvEditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("advId");
        String currentUser = request.getParameter("currentUser");
        Adv adv = null;
        try (AdvStore advStore = new AdvStore()) {
            adv = advStore.selectAdvById(id);
            advStore.complete();
        }

        request.setAttribute("adv", adv);
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/editAd.BAK.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String advId = request.getParameter("advId");
        String titel = request.getParameter("titel");
        String kategorie = request.getParameter("kategorie");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String currentUser = request.getParameter("currentUser");
        titel = titel.replace("'", "&#39");
        description = description.replace("'", "&#39");

        //upadte adv
        AdvStore advStore = new AdvStore();
        advStore.updateAdv(advId, titel, kategorie, price, description, currentUser);
        advStore.complete();

        List<Adv> advList = null;
        advList = advStore.selectAllAdv();
        advStore.complete();


        request.setAttribute("advList", advList);
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/shop.ftl").forward(request, response);
    }
}
