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


/**
 * Einfaches Beispiel, das die Verwendung des {@link UserStore}s zeigt.
 */
public final class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String currentUser = request.getParameter("currentUser");
        User user = null;

        UserStore userStore = new UserStore();
        user = userStore.selectUserByUserName(userName);
        userStore.complete();

        int total = 0;
        AdvStore advStore = new AdvStore();
        List<Adv> soldList = null;
        List<Adv> offerList = null;
        List<Adv> purchaseList = null;
        soldList = advStore.selectSoldAdvByUser(userName);
        purchaseList = advStore.selectPurcheAdvByUser(userName);
        offerList = advStore.selectOffesAdvByUser(userName);

        total = advStore.selectAdvCountOfUser(userName);

        advStore.complete();

        request.setAttribute("user", user);
        request.setAttribute("purchaseList", purchaseList);
        request.setAttribute("offerList", offerList);
        request.setAttribute("soldNum", soldList.size());

        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/userProfile.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String currentUser = request.getParameter("currentUser");
        User user = null;

        UserStore userStore = new UserStore();
        user = userStore.selectUserByUserName(userName);
        userStore.complete();

        int total = 0;
        AdvStore advStore = new AdvStore();
        List<Adv> soldList = null;
        List<Adv> offerList = null;
        soldList = advStore.selectSoldAdvByUser(userName);

        offerList = advStore.selectOffesAdvByUser(userName);

        total = advStore.selectAdvCountOfUser(userName);

        advStore.complete();

        request.setAttribute("user", user);
        request.setAttribute("soldList", soldList);
        request.setAttribute("offerList", offerList);
        request.setAttribute("total", offerList.size());
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/userProfile.ftl").forward(request, response);
    }

}
