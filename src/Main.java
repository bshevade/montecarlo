import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int NUMBER_OF_YEARS = 20;
    public static int SIMULATION_COUNT = 10000;
    public static double INFLATION_RATE = 3.5;
    public static double INITIAL_AMOUNT = 100000;

    public static void main(String[] args) {
        List<Portfolio> portfolioList = new ArrayList();
        portfolioList.add(new Portfolio(PortfolioType.AGGRESSIVE, 9.4324, 15.675));
        portfolioList.add(new Portfolio(PortfolioType.CONSERVATIVE, 6.189, 6.3438));

        portfolioList.forEach(portfolio -> {
            System.out.println("Portfolio Type: " + portfolio.type);
            List<Double> simulatedFutureReturns = new ArrayList();
            for(int j = 0; j < SIMULATION_COUNT; j++) {
                double previousYearValue = INITIAL_AMOUNT;
                double futureReturn = previousYearValue;
                for(int i = 1; i<= NUMBER_OF_YEARS; i++) {
                    double futureReturnPercent = Utils.gaussian(portfolio.mean, portfolio.standardDeviation);
                    //adjust for inflation
                    //rate of return = ((1+return)/(1+inflationRate))-1
                    futureReturnPercent = ((1+futureReturnPercent/100)/(1+INFLATION_RATE/100))-1;
                    double futureReturnDelta = (futureReturnPercent) * previousYearValue;
                    if(futureReturnPercent < 0) {
                        futureReturn -= futureReturnDelta;
                    } else {
                        futureReturn += futureReturnDelta;
                    }

                    previousYearValue = futureReturn;
                }

                simulatedFutureReturns.add(futureReturn);
            }

            //sort the simulated runs to get the median and percentile values.
            Collections.sort(simulatedFutureReturns);
            //save the array to plot a graph in excel later.
            Arrays.asList(simulatedFutureReturns);

            System.out.println("median: " + simulatedFutureReturns.get(simulatedFutureReturns.size()/2));
            System.out.println("90th percentile: " + simulatedFutureReturns.get(9000));
            System.out.println("10th percentile: " + simulatedFutureReturns.get(1000));
            System.out.println("###########");
        });

    }
}
