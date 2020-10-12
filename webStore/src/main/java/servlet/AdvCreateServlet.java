package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.stores.AdvStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class AdvCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("currentUser");
        request.setAttribute("currentUser", userName);
        request.getRequestDispatcher("/createAd.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titel = request.getParameter("titel");
        titel = titel.replace("'", "&#39");
        String kategorie = request.getParameter("kategorie");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        description = description.replace("'", "&#39");
        String currentUser = request.getParameter("currentUser");

        //insert adv
        AdvStore advStore = new AdvStore();
        advStore.addAdv(titel, kategorie, price, description, currentUser);

        //get Adv Detail
        Adv adv = new Adv();
        adv.setErsteller(currentUser);

        adv.setStatus("aktiv");
        adv.setErstellungsdatum(Timestamp.valueOf("2019-01-18 15:03:55.146448"));
        adv.setPreis(new BigDecimal(price));
        adv.setText(description);
        adv.setTitel(titel);


        List<Adv> advList = null;

        advList = advStore.selectAllAdv();
        advStore.complete();


        request.setAttribute("advList", advList);
        request.setAttribute("adv", adv);

        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/shop.ftl").forward(request, response);
    }
}
