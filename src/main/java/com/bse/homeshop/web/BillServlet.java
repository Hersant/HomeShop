package com.bse.homeshop.web;

import com.bse.homeshop.Fridge;
import com.bse.homeshop.Product;
import com.bse.homeshop.Television;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillServlet extends HttpServlet {
    List<Product> products = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Product tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189, 130, false);
        products.add(cafe);
        products.add(tv);
        products.add(fridge);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        if (request.getQueryString() == null) {
            displayForm(response);
        } else {
            displayBill(request, response);
        }
    }

    private void displayBill(HttpServletRequest request, HttpServletResponse response) {

    }

    private void displayForm(HttpServletResponse response) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            response.getWriter().println("<b>" + (i+1) + " - " + product.getName()
            + "<b> : " + product.getPrice() + "<br>" + product.getDescription() + "<br/><br/>");
        }
        String form = "<form action=\"bill\">" +
                "<b>nom complet :</b> <input name=\"fullname\"/><br/>" +
                "<b>adresse :</b> <input name=\"address\"/><br/><br/>" +
                "<b>livraison :</b> <br/>" +
                "à domicile : <input type=\"radio\" name=\"deliveryMode\" value=\"direct\"/><br/>" +
                "express : <input type=\"radio\" name=\"deliveryMode\" value=\"express\"/><br/>" +
                "point relais : <input type=\"radio\" name=\"deliveryMode\" value=\"relay\"/><br/>" +
                "à retirer : <input type=\"radio\" name=\"deliveryMode\" value=\"takeAway\"/><br/>" +
                "<b>Informations livraison</b> (relay et express) : <input name=\"deliveryInfo\"/><br/><br/>" +
                "<b>liste produits </b> (produit:quantité, un produit par ligne) : <br/>" +
                "<textarea name=\"products\"></textarea><br/>" +
                "<input type=\"submit\"/>" +
                "</form>";
        response.getWriter().println(form);
    }
}
