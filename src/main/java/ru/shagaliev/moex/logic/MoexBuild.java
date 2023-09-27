package ru.shagaliev.moex.logic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.shagaliev.moex.logic.data.*;
import ru.shagaliev.moex.models.Moex;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
//@Component
public class MoexBuild {
    private final Parse parse=new Parse();

//    public MoexBuild(Parse parse) {
//        this.parse = parse;
//    }

    public List<Moex> build() {
        List<Moex> moexList = new ArrayList<>();
        List<Capital> capitalList = parse.parseCapital();
        List<CleanIncome> cleanIncomeList = parse.parseCleanIncome();
        List<OperatingIncome> operatingIncomeList = parse.parseOperatingIncome();
        List<Eps> epsList = parse.parseEps();
        List<Price> priceList = parse.parsePrice();
        List<RoePercent> roeList = parse.parseRoe();
        List<NumberOfShares> numberOfSharesList = parse.parseNumberOfShares();

        capitalList.forEach(x -> {
            Moex moex = new Moex();
            moex.setPoints(calcPoints(x, getAvrCapital(x), getOperatingIncome(operatingIncomeList, x), getAvrEps(getEps(epsList, x)), getRoe(roeList, x)));
            moex.setPoints5(calcPoints5(x, getAvrCapital5(x), getOperatingIncome(operatingIncomeList, x), getAvrEps5(getEps(epsList, x)), getRoe(roeList, x), x.getTicket()));
            moex.setName(x.getName());
            moex.setTicket(x.getTicket());
            moex.setPrice(getPrice(priceList, x));
            moex.setCapitalAvrPerYear(getAvrCapital(x));
            moex.setCapitalAvrPerYear5(getAvrCapital5(x));
            moex.setDesiredPrice(getDeciredPrice(getCleanIncome(cleanIncomeList, x), getNumberOfShares(numberOfSharesList, x), 20));
            moex.setPercentYear(getPercentYear(getPe(getCleanIncome(cleanIncomeList, x), getNumberOfShares(numberOfSharesList, x), getPrice(priceList, x))));
            moex.setCountShares(getNumberOfShares(numberOfSharesList, x));
            moex.setOperatingIncomeAvrPerYear(getAvrOperatingIncome(getOperatingIncome(operatingIncomeList, x)));
            moex.setCleanIncomeAvrPerYear(getAvrCleanIncome(getCleanIncome(cleanIncomeList, x)));
            moex.setPE(getPe(getCleanIncome(cleanIncomeList, x), getNumberOfShares(numberOfSharesList, x), getPrice(priceList, x)));
            moex.setEpsAvrPerYear(getAvrEps(getEps(epsList, x)));
            moex.setEpsAvrPerYear5(getAvrEps5(getEps(epsList, x)));
            moex.setCapitalCount1Year(x.getCapitalCount1Year());
            moex.setCapitalCount2Year(x.getCapitalCount2Year());
            moex.setCapitalCount3Year(x.getCapitalCount3Year());
            moex.setCapitalCount4Year(x.getCapitalCount4Year());
            moex.setCapitalCount5Year(x.getCapitalCount5Year());
            moex.setCapitalCount6Year(x.getCapitalCount6Year());
            moex.setCapitalCount7Year(x.getCapitalCount7Year());
            moex.setCapitalCount8Year(x.getCapitalCount8Year());
            moex.setCapitalCount9Year(x.getCapitalCount9Year());
            moex.setCapitalCount10Year(x.getCapitalCount10Year());
            moex.setCleanIncomeCount1Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount1Year());
            moex.setCleanIncomeCount2Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount2Year());
            moex.setCleanIncomeCount3Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount3Year());
            moex.setCleanIncomeCount4Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount4Year());
            moex.setCleanIncomeCount5Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount5Year());
            moex.setCleanIncomeCount6Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount6Year());
            moex.setCleanIncomeCount7Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount7Year());
            moex.setCleanIncomeCount8Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount8Year());
            moex.setCleanIncomeCount9Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount9Year());
            moex.setCleanIncomeCount10Year(isNull(getCleanIncome(cleanIncomeList, x)) ? 0.0 : getCleanIncome(cleanIncomeList, x).getCleanIncomeCount10Year());
            moex.setOperatingIncomeCount1Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount1Year());
            moex.setOperatingIncomeCount2Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount2Year());
            moex.setOperatingIncomeCount3Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount3Year());
            moex.setOperatingIncomeCount4Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount4Year());
            moex.setOperatingIncomeCount5Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount5Year());
            moex.setOperatingIncomeCount6Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount6Year());
            moex.setOperatingIncomeCount7Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount7Year());
            moex.setOperatingIncomeCount8Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount8Year());
            moex.setOperatingIncomeCount9Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount9Year());
            moex.setOperatingIncomeCount10Year(isNull(getOperatingIncome(operatingIncomeList, x)) ? 0.0 : getOperatingIncome(operatingIncomeList, x).getOperatingIncomeCount10Year());
            moex.setEpsCount1Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount1Year());
            moex.setEpsCount2Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount2Year());
            moex.setEpsCount3Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount3Year());
            moex.setEpsCount4Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount4Year());
            moex.setEpsCount5Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount5Year());
            moex.setEpsCount6Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount6Year());
            moex.setEpsCount7Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount7Year());
            moex.setEpsCount8Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount8Year());
            moex.setEpsCount9Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount9Year());
            moex.setEpsCount10Year(isNull(getEps(epsList, x)) ? 0.0 : getEps(epsList, x).getEpsCount10Year());
            moex.setRoePercent1Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent1Year());
            moex.setRoePercent2Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent2Year());
            moex.setRoePercent3Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent3Year());
            moex.setRoePercent4Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent4Year());
            moex.setRoePercent5Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent5Year());
            moex.setRoePercent6Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent6Year());
            moex.setRoePercent7Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent7Year());
            moex.setRoePercent8Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent8Year());
            moex.setRoePercent9Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent9Year());
            moex.setRoePercent10Year(isNull(getRoe(roeList, x)) ? 0.0 : getRoe(roeList, x).getRoePercent10Year());


            moexList.add(moex);
        });

        return moexList;
    }

    private int calcPoints5(Capital x, double avrCapital5, OperatingIncome operatingIncome, double avrEps5, RoePercent roe, String ticket) {
        int result = 0;
        if (avrCapital5 >= 10 && avrCapital5 < 1000) {
            result++;
        }
        if (avrEps5 >= 10 && avrEps5 < 1000) {
            result++;
        }
        if (avrEps5 < 5) {
            result--;
        }
        if (nonNull(x) && nonNull(operatingIncome)) {
            double diviPO5 = operatingIncome.getOperatingIncomeCount6Year() / x.getCapitalCount6Year() * 100;
            double diviPO10 = operatingIncome.getOperatingIncomeCount10Year() / x.getCapitalCount10Year() * 100;
            if (diviPO10 >= 14) {
                result++;
            }
            if (diviPO10 < 6) {
                result--;
            }
            if (diviPO10 > diviPO5) {
                result++;
            }
        }
        if (nonNull(roe)) {
            if (roe.getRoePercent10Year() >= 16) {
                result++;
            }
            if (roe.getRoePercent10Year() < 10) {
                result--;
            }
            if (roe.getRoePercent10Year() > roe.getRoePercent6Year()) {
                result++;
            }
        }

        return result;
    }

    private double getAvrEps5(Eps eps) {
        double result = 0.0;
        if (nonNull(eps)) {
            result = Math.floor((Math.pow(eps.getEpsCount10Year() / eps.getEpsCount6Year(), 0.25) - 1) * 100);
        }
        return result;
    }

    private double getAvrCapital5(Capital capital) {
        double result = 0.0;
        if (nonNull(capital)) {
            result = Math.floor((Math.pow(capital.getCapitalCount10Year() / capital.getCapitalCount6Year(), 0.25) - 1) * 100);
        }
        return result;
    }

    private int calcPoints(Capital x, double avrCapital, OperatingIncome operatingIncome, double avrEps, RoePercent roe) {
        int result = 0;
        if (avrCapital >= 10 && avrCapital < 1000) {
            result++;
        }
        if (avrEps >= 10 && avrEps < 1000) {
            result++;
        }
        if (avrEps < 5) {
            result--;
        }
        if (nonNull(x) && nonNull(operatingIncome)) {
            double diviPO1 = operatingIncome.getOperatingIncomeCount1Year() / x.getCapitalCount1Year() * 100;
            double diviPO10 = operatingIncome.getOperatingIncomeCount10Year() / x.getCapitalCount10Year() * 100;
            if (diviPO10 >= 14) {
                result++;
            }
            if (diviPO10 < 6) {
                result--;
            }
            if (diviPO10 > diviPO1) {
                result++;
            }
        }
        if (nonNull(roe)) {
            if (roe.getRoePercent10Year() >= 16) {
                result++;
            }
            if (roe.getRoePercent10Year() < 10) {
                result--;
            }
            if (roe.getRoePercent10Year() > roe.getRoePercent1Year()) {
                result++;
            }
        }

        return result;
    }

    private double getDeciredPrice(CleanIncome cleanIncome, Double count, int wantPrice) {
        double result = 0.0;
        if (nonNull(cleanIncome)) {
            result = round(cleanIncome.getCleanIncomeCount10Year() / count * 100 / wantPrice * 1000,2);
        }
        return result;
    }

    private double getPercentYear(Double price) {
        return round(100 / price,2);
    }

    private double getPe(CleanIncome cleanIncome, Double count, Double price) {
        double result = 0.0;
        if (nonNull(cleanIncome)) {
            result = round(price / (cleanIncome.getCleanIncomeCount10Year() / count * 1000),1);
        }
        return result;
    }

    private double getAvrEps(Eps eps) {
        double result = 0.0;
        if (nonNull(eps)) {
            result = Math.floor((Math.pow(eps.getEpsCount10Year() / eps.getEpsCount1Year(), 0.1111111111) - 1) * 100);
        }
        return result;
    }

    private double getAvrOperatingIncome(OperatingIncome operatingIncome) {
        double result = 0.0;
        if (nonNull(operatingIncome)) {
            result = Math.floor((Math.pow(operatingIncome.getOperatingIncomeCount10Year() / operatingIncome.getOperatingIncomeCount1Year(), 0.111111111) - 1) * 100);
        }
        return result;
    }

    private double getAvrCleanIncome(CleanIncome cleanIncome) {
        double result = 0.0;
        if (nonNull(cleanIncome)) {
            result = Math.floor((Math.pow(cleanIncome.getCleanIncomeCount10Year() / cleanIncome.getCleanIncomeCount1Year(), 0.111111111) - 1) * 100);
        }
        return result;
    }

    private double getAvrCapital(Capital capital) {
        double result = 0.0;
        if (nonNull(capital)) {
            result = Math.floor((Math.pow(capital.getCapitalCount10Year() / capital.getCapitalCount1Year(), 0.11111111) - 1) * 100);
        }
        return result;
    }

    private RoePercent getRoe(List<RoePercent> roeList, Capital x) {
        return roeList.stream()
                .filter(y -> y.getTicket().equals(x.getTicket()))
                .findAny()
                .orElse(null);
    }

    private double getNumberOfShares(List<NumberOfShares> numberOfSharesList, Capital x) {
        return numberOfSharesList.stream()
                .filter(y -> y.getTicket().equals(x.getTicket()))
                .map(z -> z.getCount())
                .findAny()
                .orElse(0.0);
    }

    private Eps getEps(List<Eps> epsList, Capital x) {
        return epsList.stream()
                .filter(y -> y.getTicket().equals(x.getTicket()))
                .findAny()
                .orElse(null);
    }

    private OperatingIncome getOperatingIncome(List<OperatingIncome> operatingIncomeList, Capital x) {
        return operatingIncomeList.stream()
                .filter(y -> y.getTicket().equals(x.getTicket()))
                .findAny()
                .orElse(null);
    }

    private CleanIncome getCleanIncome(List<CleanIncome> cleanIncomeList, Capital x) {
        return cleanIncomeList.stream()
                .filter(y -> y.getTicket().equals(x.getTicket()))
                .findAny()
                .orElse(null);
    }

    private Double getPrice(List<Price> priceList, Capital x) {
        return priceList.stream()
                .filter(y -> y.getTicket().equals(x.getTicket()))
                .map(z -> z.getPrice())
                .findAny()
                .orElse(0.0);
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
