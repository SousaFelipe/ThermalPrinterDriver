package com.flpss.printer;


import javax.print.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Printer {



    private static final char ESC               = 27; //escape
    private static final char AT                = 64; //@
    private static final char LINE_FEED         = 10; //line feed/new line
    private static final char PARENTHESIS_LEFT  = 40;
    private static final char BACKSLASH         = 92;
    private static final char CR                = 13;  //carriage return
    private static final char TAB               = 9;   //horizontal tab
    private static final char FF                = 12;  //form feed
    private static final char P                 = 80;  //10cpi pitch
    private static final char M                 = 77;  //12cpi pitch
    private static final char g                 = 103; //15cpi pitch
    private static final char p                 = 112; //used for choosing proportional mode or fixed-pitch
    private static final char t                 = 116; //used for character set assignment/selection
    private static final char l                 = 108; //used for setting left margin
    private static final char x                 = 120; //used for setting draft or letter quality (LQ) printing
    private static final char E                 = 69;  //bold font on
    private static final char F                 = 70;  //bold font off
    private static final char J                 = 74;  //used for advancing paper vertically
    private static final char Q                 = 81;  //used for setting right margin
    private static final char $                 = 36;  //used for absolute horizontal positioning
    public static final char ITALIC_ON          = 52;  //set font italic
    public static final char ITALIC_OFF         = 53;  //unset font italic
    public static final char CONDENSED_ON       = 15;
    public static final char CONDENSED_OFF      = 18;



    private FileOutputStream printerStream;
    private final PrintService printer;



    public Printer(PrintService printer) throws Exception {
        if (printer == null) {
            throw new Exception("The printer cannot be initialized with an empty service!");
        }
        else {
            this.printer = printer;
        }
    }



    public boolean open(String port) {
        try {
            this.printerStream = new FileOutputStream(port);
            return true;
        }
        catch (FileNotFoundException e) {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }



    public boolean print(String data) {

        try {
            DocPrintJob dpj = this.printer.createPrintJob();
            InputStream stream = new ByteArrayInputStream((data + "\n").getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, null);
            dpj.print(doc, null);
            return true;
        }
        catch (PrintException e) {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }



    public boolean close() {
        try {
            this.printerStream.flush();
            this.printerStream.close();
            return true;
        }
        catch (IOException e) {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
