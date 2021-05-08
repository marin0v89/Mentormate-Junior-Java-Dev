package core;

public class Employee {
    private String name;
    private long totalSales;
    private long salesPeriod;
    private double experienceMultiplier;

    public Employee(String name, long totalSales, long salesPeriod, double experienceMultiplier) {
        this.setName(name);
        this.setTotalSales(totalSales);
        this.setSalesPeriod(salesPeriod);
        this.setExperienceMultiplier(experienceMultiplier);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    private void setTotalSales(long totalSales) {
        checkData(totalSales);
        this.totalSales = totalSales;
    }

    private void setSalesPeriod(long salesPeriod) {
        checkData(salesPeriod);
        this.salesPeriod = salesPeriod;
    }

    private void setExperienceMultiplier(double experienceMultiplier) {
        if (experienceMultiplier <= 0) {
            throw new IllegalArgumentException();
        }
        this.experienceMultiplier = experienceMultiplier;
    }

    private void checkData(long data) {
        if (data <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public long getSalesPeriod() {
        return this.salesPeriod;
    }

    public double getExperienceMultiplier() {
        return this.experienceMultiplier;
    }

    public long getTotalSales() {
        return this.totalSales;
    }
}
