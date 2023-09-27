package ru.shagaliev.moex.logic;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.shagaliev.moex.logic.data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Parse {
    private static String PRICE_DIR="https://smart-lab.ru/q/shares/";
    private static String CAPITAL_DIR = "https://smart-lab.ru/q/shares_fundamental4/?field=revenue";
    private static String EPS_DIR = "https://smart-lab.ru/q/shares_fundamental4/?field=eps";
    private static String OPERATING_INCOME_DIR = "https://smart-lab.ru/q/shares_fundamental4/?field=operating_income";
    private static String CLEAN_INCOME_DIR = "https://smart-lab.ru/q/shares_fundamental4/?field=net_income";
    private static String ROE_DIR ="https://smart-lab.ru/q/shares_fundamental4/?field=roe";
    private static String NUMBER_OF_SHARES_DIR ="https://smart-lab.ru/q/shares_fundamental4/?field=number_of_shares";

    public List<Price> parsePrice(){
        Set<Price> priceSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(PRICE_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                Price price= new Price();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1) {
                    price.setTicket(nodes.get(5).childNodes().get(0).toString());
                    price.setPrice(getDouble(nodes, 13));
                    priceSet.add(price);
                }
            }
            log.info("price complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getListFromSet(priceSet);
    }

    public List<NumberOfShares> parseNumberOfShares(){
        Set<NumberOfShares> numberOfSharesSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(NUMBER_OF_SHARES_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                NumberOfShares numberOfShares= new NumberOfShares();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1) {
                    numberOfShares.setTicket(nodes.get(5).childNodes().get(0).toString());
                    numberOfShares.setCount(getDouble(nodes, 29));
                    numberOfSharesSet.add(numberOfShares);
                }
            }
            log.info("numberOfShares complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getListFromSet(numberOfSharesSet);
    }

    public List<Capital> parseCapital(){
        Set<Capital> capitalSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(CAPITAL_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                Capital capital=new Capital();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1 && nodes.get(5).childNodes().get(0).toString().length()<8)  {
                    capital.setNumber(nodes.get(1).childNodes().get(0).toString());
                    capital.setName(nodes.get(3).childNodes().get(0).toString());
                    capital.setTicket(nodes.get(5).childNodes().get(0).toString());
                    capital.setCapitalCount1Year(getDouble(nodes, 11));
                    capital.setCapitalCount2Year(getDouble(nodes, 13));
                    capital.setCapitalCount3Year(getDouble(nodes, 15));
                    capital.setCapitalCount4Year(getDouble(nodes, 17));
                    capital.setCapitalCount5Year(getDouble(nodes, 19));
                    capital.setCapitalCount6Year(getDouble(nodes, 21));
                    capital.setCapitalCount7Year(getDouble(nodes, 23));
                    capital.setCapitalCount8Year(getDouble(nodes, 25));
                    capital.setCapitalCount9Year(getDouble(nodes, 27));
                    capital.setCapitalCount10Year(getDouble(nodes, 29));
                    capitalSet.add(capital);
                }
            }
            log.info("capital complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getListFromSet(capitalSet);
    }

    public List<Eps> parseEps(){
        Set<Eps> epsSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(EPS_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                Eps eps=new Eps();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1)  {
                    eps.setNumber(nodes.get(1).childNodes().get(0).toString());
                    eps.setName(nodes.get(3).childNodes().get(0).toString());
                    eps.setTicket(nodes.get(5).childNodes().get(0).toString());
                    eps.setEpsCount1Year(getDouble(nodes, 11));
                    eps.setEpsCount2Year(getDouble(nodes, 13));
                    eps.setEpsCount3Year(getDouble(nodes, 15));
                    eps.setEpsCount4Year(getDouble(nodes, 17));
                    eps.setEpsCount5Year(getDouble(nodes, 19));
                    eps.setEpsCount6Year(getDouble(nodes, 21));
                    eps.setEpsCount7Year(getDouble(nodes, 23));
                    eps.setEpsCount8Year(getDouble(nodes, 25));
                    eps.setEpsCount9Year(getDouble(nodes, 27));
                    eps.setEpsCount10Year(getDouble(nodes, 29));
                    epsSet.add(eps);
                }
            }
            log.info("eps complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getListFromSet(epsSet);
    }

    public List<CleanIncome> parseCleanIncome(){
        Set<CleanIncome> cleanIncomeSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(CLEAN_INCOME_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                CleanIncome cleanIncome=new CleanIncome();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1)  {
                    cleanIncome.setNumber(nodes.get(1).childNodes().get(0).toString());
                    cleanIncome.setName(nodes.get(3).childNodes().get(0).toString());
                    cleanIncome.setTicket(nodes.get(5).childNodes().get(0).toString());
                    cleanIncome.setCleanIncomeCount1Year(getDouble(nodes, 11));
                    cleanIncome.setCleanIncomeCount2Year(getDouble(nodes, 13));
                    cleanIncome.setCleanIncomeCount3Year(getDouble(nodes, 15));
                    cleanIncome.setCleanIncomeCount4Year(getDouble(nodes, 17));
                    cleanIncome.setCleanIncomeCount5Year(getDouble(nodes, 19));
                    cleanIncome.setCleanIncomeCount6Year(getDouble(nodes, 21));
                    cleanIncome.setCleanIncomeCount7Year(getDouble(nodes, 23));
                    cleanIncome.setCleanIncomeCount8Year(getDouble(nodes, 25));
                    cleanIncome.setCleanIncomeCount9Year(getDouble(nodes, 27));
                    cleanIncome.setCleanIncomeCount10Year(getDouble(nodes, 29));
                    cleanIncomeSet.add(cleanIncome);
                }
            }
            log.info("cleanIncome complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getListFromSet(cleanIncomeSet);
    }

    public List<OperatingIncome> parseOperatingIncome(){
        Set<OperatingIncome> operatingIncomeSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(OPERATING_INCOME_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                OperatingIncome operatingIncome=new OperatingIncome();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1)  {
                    operatingIncome.setNumber(nodes.get(1).childNodes().get(0).toString());
                    operatingIncome.setName(nodes.get(3).childNodes().get(0).toString());
                    operatingIncome.setTicket(nodes.get(5).childNodes().get(0).toString());
                    operatingIncome.setOperatingIncomeCount1Year(getDouble(nodes, 11));
                    operatingIncome.setOperatingIncomeCount2Year(getDouble(nodes, 13));
                    operatingIncome.setOperatingIncomeCount3Year(getDouble(nodes, 15));
                    operatingIncome.setOperatingIncomeCount4Year(getDouble(nodes, 17));
                    operatingIncome.setOperatingIncomeCount5Year(getDouble(nodes, 19));
                    operatingIncome.setOperatingIncomeCount6Year(getDouble(nodes, 21));
                    operatingIncome.setOperatingIncomeCount7Year(getDouble(nodes, 23));
                    operatingIncome.setOperatingIncomeCount8Year(getDouble(nodes, 25));
                    operatingIncome.setOperatingIncomeCount9Year(getDouble(nodes, 27));
                    operatingIncome.setOperatingIncomeCount10Year(getDouble(nodes, 29));
                    operatingIncomeSet.add(operatingIncome);
                }
            }
            log.info("operatingIncome complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getListFromSet(operatingIncomeSet);
    }

    public List<RoePercent> parseRoe(){
        Set<RoePercent> roePercentSet = new HashSet<>();
        try {
            Elements select = Jsoup.connect(ROE_DIR).get().select("table > tbody >tr");
            for (Element el:select) {
                RoePercent roePercent=new RoePercent();
                List<Node> nodes = el.childNodes();
                if(nodes.stream().count()>1)  {
                    roePercent.setNumber(nodes.get(1).childNodes().get(0).toString());
                    roePercent.setName(nodes.get(3).childNodes().get(0).toString());
                    roePercent.setTicket(nodes.get(5).childNodes().get(0).toString());
                    roePercent.setRoePercent1Year(getDouble(nodes, 11));
                    roePercent.setRoePercent2Year(getDouble(nodes, 13));
                    roePercent.setRoePercent3Year(getDouble(nodes, 15));
                    roePercent.setRoePercent4Year(getDouble(nodes, 17));
                    roePercent.setRoePercent5Year(getDouble(nodes, 19));
                    roePercent.setRoePercent6Year(getDouble(nodes, 21));
                    roePercent.setRoePercent7Year(getDouble(nodes, 23));
                    roePercent.setRoePercent8Year(getDouble(nodes, 25));
                    roePercent.setRoePercent9Year(getDouble(nodes, 27));
                    roePercent.setRoePercent10Year(getDouble(nodes, 29));
                    roePercentSet.add(roePercent);
                }
            }
            log.info("roe complite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getListFromSet(roePercentSet);
    }

    private <T> List<T> getListFromSet(Set<T> hashSet) {
        List<T> list = new ArrayList<>();
        list.addAll(hashSet);
        return list;
    }

    private double getDouble(List<Node> nodes, int index) {
        double count = 0.0F;
        try {
            count = Double.parseDouble(nodes.get(index).childNodes().get(0).toString().replaceAll("\\s|%", ""));
        } catch (Exception ex) {

        }
        return count;
    }
}
