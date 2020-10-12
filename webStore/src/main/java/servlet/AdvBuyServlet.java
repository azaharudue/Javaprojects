package servlet;

import com.alibaba.fastjson.JSONObject;
import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.stores.AdvStore;
import de.unidue.inf.is.stores.BuyRecordStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdvBuyServlet extends HttpServlet {

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
        //check the item status first
        JSONObject jo = new JSONObject();
        if (adv.getStatus().contains("aktiv")) {
            //active so that add a buy record and return success
            //when a buy record was added, the commondity status change to sold automatically becausee of the trigger
            BuyRecordStore buyRecordStore = new BuyRecordStore();
            buyRecordStore.addBuyRecord(currentUser, id);
            buyRecordStore.complete();

            jo.put("code", "000");
            jo.put("msg", "Transaction successful :-) Check it in the purchase list of your profile! ");
        } else {
            jo.put("code", "999");
            jo.put("msg", "Sorry :-( , Adv is not longer active.");
        }

        response.setContentType("application/json;charset=utf-8;");
        response.getWriter().print(jo);
    }

}