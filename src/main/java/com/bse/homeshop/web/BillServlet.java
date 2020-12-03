package com.bse.homeshop.web;

import com.bse.homeshop.*;
import com.bse.homeshop.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillServlet extends HttpServlet {
    List<Product> products = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        /*Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Product tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189, 130, false);
        products.add(cafe);
        products.add(tv);
        products.add(fridge);*/
        products = new ProductDAO().getAll();
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

    /**
     * Split parameters into key-value pairs
     * @param queryString Parameters got from the url
     * @return A map of parameters
     */
    public Map<String, String> splitParameters(String queryString) {
        String[] brutParams = queryString.split("&");
        Map<String, String> mapParams = new HashMap<>();

        for (String brutParam: brutParams) {
            String[] keyVal = brutParam.split("=");
            if (keyVal.length == 2) {
                mapParams.put(keyVal[0], keyVal[1]);
            }
        }
        return mapParams;
    }

    private void displayBill(HttpServletRequest request, HttpServletResponse response) {
        String params = request.getQueryString();
        Map<String, String> mapParams = splitParameters(params);
        Customer customer = new Customer(mapParams.get("fullname"), mapParams.get("address"));
        Delivery delivery = null;
        switch(mapParams.get("deliveryMode")) {
            case "direct":
                delivery = new DirectDelivery();
                break;
            case "express":
                delivery = new ExpressDelivery(mapParams.get("deliveryInfo"));
                break;
            case "relay":
                delivery = new RelayDelivery(Integer.parseInt(mapParams.get("deliveryInfo")));
                break;
            case "takeaway":
                delivery = new TakeAwayDelivery();
                break;
        }

        Bill bill = new Bill(customer, delivery);

        // Getting products and their quantity
        String[] productsParams = mapParams.get("products").split("%0D%0A");
        for (String productLine: productsParams) {
            String[] productAndQuantity = productLine.split("%3A");
            int indexProduct = Integer.parseInt(productAndQuantity[0]);
            if (indexProduct < products.size()) {
                Product product = products.get(indexProduct);
                Integer quantity = Integer.parseInt(productAndQuantity[1]);
                bill.addProduct(product, quantity);
            }
        }

        // Generating bill
        bill.generate(new Writer() {
            @Override
            public void start() {

            }

            @Override
            public void writeLine(String line) {
                try {
                    response.getWriter().println("<br>" + line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void stop() {

            }
        });
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
