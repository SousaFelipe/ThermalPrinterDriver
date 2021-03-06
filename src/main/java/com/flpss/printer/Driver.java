package com.flpss.printer;


import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Driver {



    public static List<PrintService> getPrinters() {
        List<PrintService> printers = new ArrayList<>();

        try {
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
            printers.addAll(Arrays.asList(services));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return printers;
    }



    public static List<String> getPrinterNames() {
        List<String> printerNames = new ArrayList<>();

        if (Driver.getPrinters().size() > 0) {
            for (PrintService service: Driver.getPrinters()) {
                printerNames.add(service.getName());
            }
        }

        return printerNames;
    }



    public PrintService getService(String selectedPrinter) {
        PrintService printer = null;

        if (Driver.getPrinters().size() <= 0) {
            return null;
        }

        for (PrintService service : Driver.getPrinters()) {
            if (service.getName() != null && service.getName().contains(selectedPrinter)) {
                printer = service;
            }
        }

        return printer;
    }
}
