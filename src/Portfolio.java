public class Portfolio {

    PortfolioType type;
    double mean;
    double standardDeviation;

    public Portfolio(final PortfolioType type, final double mean,
        final double standardDeviation) {
        this.type = type;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }
}
