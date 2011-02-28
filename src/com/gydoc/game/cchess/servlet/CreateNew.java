package com.gydoc.game.cchess.servlet;

import com.gydoc.game.JSonUtil;
import com.gydoc.game.cchess.ChineseChess;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.StringWriter;

/**
 *
 */
public class CreateNew extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serviceImpl(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serviceImpl(req, resp);
    }

    private void serviceImpl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ChineseChess game = (ChineseChess) session.getAttribute("ChineseChess");
        if (game != null) {

        }
        game = new ChineseChess();
        session.setAttribute("ChineseChess", game);
        JSonUtil.getObjectMapper().writeValue(resp.getWriter(), game);
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, new ChineseChess());
        System.out.println("sw = " + sw);
    }
    
}
