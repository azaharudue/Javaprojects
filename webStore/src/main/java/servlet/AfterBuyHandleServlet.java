package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.AdvStore;
import de.unidue.inf.is.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AfterBuyHandleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //to curruser's profile
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUser = request.getParameter("currentUser");
        User user = null;

        UserStore userStore = new UserStore();
        user = userStore.selectUserByUserName(currentUser);
        userStore.complete();

        int total = 0;
        AdvStore advStore = new AdvStore();
        List<Adv> soldList = null;
        List<Adv> offerList = null;
        List<Adv> purchaseList = null;
        soldList = advStore.selectSoldAdvByUser(currentUser);
        purchaseList = advStore.selectPurcheAdvByUser(currentUser);
        offerList = advStore.selectOffesAdvByUser(currentUser);

        total = advStore.selectAdvCountOfUser(currentUser);

        advStore.complete();

        request.setAttribute("user", user);
        request.setAttribute("purchaseList", purchaseList);
        request.setAttribute("offerList", offerList);
        request.setAttribute("soldNum", soldList.size());

        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/userProfile.ftl").forward(request, response);
    }

    //to shop page
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUser = request.getParameter("currentUser");
        response.sendRedirect("/shop?currentUser=" + currentUser);
    }
}
