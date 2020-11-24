package ch.zhaw.mas.sharingApp.firstTestProjectSetup;

public class TestFileEuro {
    private double amount;

    public TestFileEuro(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void add (TestFileEuro euro) {
        if (euro.getAmount() <0) throw new IllegalArgumentException();
        this.amount += euro.getAmount();
    }
}