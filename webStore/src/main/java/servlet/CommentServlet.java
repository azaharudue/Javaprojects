package servlet;

import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Kommentar;
import de.unidue.inf.is.stores.AdvStore;
import de.unidue.inf.is.stores.CommentStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int advId = Integer.parseInt(request.getParameter("advId"));
        String currentUser = request.getParameter("currentUser");
        String commentData = request.getParameter("commentData");
        commentData = commentData.replace("'", "&#39");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sf.format(new Date());

        Timestamp timestamp = new Timestamp(new Date().getTime());
        //Kommentar
        CommentStore commentStore = new CommentStore();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        commentStore.addComment(commentData, df.format(timestamp));

        Kommentar kommentar = commentStore.selectLastComment();

        //HatKommentar
        commentStore.addCommentBound(kommentar.getId(), currentUser, advId);

        Adv adv = null;
        try (AdvStore advStore = new AdvStore()) {
            adv = advStore.selectAdvById(String.valueOf(advId));
            advStore.complete();
        }
        List<Comment> commentList = null;

        commentList = commentStore.selectCommentOnAdvByAdvId(String.valueOf(advId));
        commentStore.complete();


        request.setAttribute("adv", adv);
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("commentList", commentList);
        request.getRequestDispatcher("/advDetail.ftl").forward(request, response);
    }
}
