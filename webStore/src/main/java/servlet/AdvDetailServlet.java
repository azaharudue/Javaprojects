package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.stores.AdvStore;
import de.unidue.inf.is.stores.CommentStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdvDetailServlet extends HttpServlet {

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
        List<Comment> commentList = null;
        try (CommentStore commentStore = new CommentStore()) {
            commentList = commentStore.selectCommentOnAdvByAdvId(id);
            commentStore.complete();
        }

        request.setAttribute("adv", adv);
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("commentList", commentList);
        request.getRequestDispatcher("/advDetail.ftl").forward(request, response);
    }
}
