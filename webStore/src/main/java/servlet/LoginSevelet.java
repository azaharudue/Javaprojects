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

public class LoginSevelet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/login.ftl").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String userName = request.getParameter("userName");
        userName = userName.replace("'", "&#39");
        String realName = request.getParameter("realName");
        realName = realName.replace("'", "&#39");

        //if user do not exist regist and login
        UserStore userStore = new UserStore();
        User user = userStore.selectUserByUserName(userName);
        if (user == null) {
            userStore.addUser(userName, realName);
            userStore.complete();
        }


        List<Adv> advList = null;
        try (AdvStore advStore = new AdvStore()) {
            advList = advStore.selectAllAdv();
            advStore.complete();
        }

        request.setAttribute("advList", advList);
        request.setAttribute("currentUser", userName);
        request.getRequestDispatcher("/shop.ftl?currentUser=" + userName).forward(request, response);
    }
}
