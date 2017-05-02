package foolkey.controller.search;

import foolkey.controller.AbstractController;
import foolkey.pojo.root.handler.search.SearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2017/5/2.
 */
@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController{

    @Autowired
    private SearchHandler handler;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        handler.execute(request, response, jsonObject);
    }
}
