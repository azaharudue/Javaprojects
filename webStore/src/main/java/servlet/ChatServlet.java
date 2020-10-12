package servlet;

import de.unidue.inf.is.domain.Chat;
import de.unidue.inf.is.stores.ChatStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatWith = request.getParameter("chatWith");
        String currentUser = request.getParameter("currentUser");

        ChatStore chatStore = new ChatStore();


        List<Chat> chatList = null;
        chatList = chatStore.getHisChat(currentUser, chatWith);
        chatStore.complete();

        request.setAttribute("chatList", chatList);
        request.setAttribute("chatWith", chatWith);
        request.setAttribute("currentUser", currentUser);

        request.getRequestDispatcher("/chat.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String chatWith = request.getParameter("chatWith");
        String currentUser = request.getParameter("currentUser");
        String chatData = request.getParameter("chatData");
        chatData = chatData.replace("'", "&#39");

        ChatStore chatStore = new ChatStore();

        chatStore.addChat(currentUser, chatWith, chatData);
        List<Chat> chatList = null;
        chatList = chatStore.getHisChat(currentUser, chatWith);
        chatStore.complete();

        request.setAttribute("chatList", chatList);
        request.setAttribute("chatWith", chatWith);
        request.setAttribute("currentUser", currentUser);

        request.getRequestDispatcher("/chat.ftl").forward(request, response);
    }

}