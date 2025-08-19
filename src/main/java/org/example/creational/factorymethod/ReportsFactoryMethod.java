package org.example.creational.factorymethod;

import java.util.Scanner;

interface Report {
    void generate();
}

class SalesReport implements Report {
    @Override
    public void generate() {
        System.out.println("Generating Sales Report");
    }
}

class InventoryReport implements Report {
    @Override
    public void generate() {
        System.out.println("Generating Inventory Report");
    }
}

abstract class ReportFactory {
    protected abstract Report createReport();

    void generateReport() {
        final Report report = this.createReport();
        report.generate();
    }
}

class SalesReportFactory extends ReportFactory {
    protected Report createReport() {
        return new SalesReport();
    }
}

class InventoryReportFactory extends ReportFactory {
    protected Report createReport() {
        return new  InventoryReport();
    }
}

// 5. Código Cliente para Probar
public class ReportsFactoryMethod {
    public static void main(String[] args) {
        ReportFactory reportFactory;

        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Qué tipo de reporte deseas? (sales/inventory): ");
        String reportType = scanner.nextLine();

        if ("sales".equalsIgnoreCase(reportType)) {
            reportFactory = new SalesReportFactory();
        } else {
            reportFactory = new InventoryReportFactory();
        }

        reportFactory.generateReport();
    }
}

