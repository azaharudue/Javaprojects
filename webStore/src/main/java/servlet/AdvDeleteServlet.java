package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.stores.AdvStore;
import de.unidue.inf.is.stores.CommentStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdvDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("advId");
        String currentUser = request.getParameter("currentUser");

        //delete comment first
        CommentStore commentStore = new CommentStore();
        commentStore.deleteCommentByAdvId(id);

        Adv adv = null;
        try (AdvStore advStore = new AdvStore()) {
            advStore.deleteAdv(id);
            advStore.complete();
        }

        List<Adv> advList = null;
        try (AdvStore advStore = new AdvStore()) {
            advList = advStore.selectAllAdv();
            advStore.complete();
        }

        request.setAttribute("advList", advList);
        request.setAttribute("currentUser", currentUser);

        request.getRequestDispatcher("/shop.ftl").forward(request, response);
    }

}