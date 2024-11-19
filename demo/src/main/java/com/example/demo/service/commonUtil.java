package com.example.demo.service;

import com.example.demo.model.Resident;
import com.example.demo.repo.ResidentRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class commonUtil {

    private ResidentRepository resRepo;

    public commonUtil(ResidentRepository resRepo) {
        this.resRepo = resRepo;
    }

    public static class MySheetHandler extends DefaultHandler {
        private final SharedStringsTable shared;
        private List<String> currentRow = new ArrayList<>();
        private List<List<String>> nonEmpty = new ArrayList<>();
        private boolean isCellValue;
        private String currentCellValue;

        public MySheetHandler(SharedStringsTable shared) {
            this.shared = shared;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attr) {
//            System.out.println("callStart = " + qName);
            if (qName.equals("c")) {
                currentCellValue = "";
            }
            isCellValue = qName.equals("v");
        }

        @Override
        public void characters(char[] ch, int start, int length) {
//            System.out.println("call char");
            System.out.println(ch);
            if (isCellValue) {
                System.out.println("start " + ch[922]);
                System.out.println("start " + start + " length " + length + " ch " + ch[1261]);
                System.out.println("start " + ch[1262]);
                currentCellValue += new String(ch, start, length);
            }
//            System.out.println(currentCellValue);
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
//            System.out.println("call end");
            if (qName.equals("v")) {
                currentRow.add(currentCellValue);
                isCellValue = false;
            } else if (qName.equals("c") && currentCellValue.isEmpty()) {
                currentRow.add("");
            } else if (qName.equals("row")) {
                if (currentRow.stream().anyMatch(cell -> !cell.isEmpty())) {
                    nonEmpty.add(new ArrayList<>(currentRow));
                }
                currentRow.clear();
            }
        }

        public List<List<String>> getData() {
            return nonEmpty;
        }
    }


    public List<Resident> findResByBrgy(String brgyCode) {
        return resRepo.findByBrgyCode(brgyCode);
    }

    public boolean importResident() {
        return false;
    }

    private boolean checkIfCellisBlank(Cell cell) {
        if (cell == null) {
            return true;
        } else {
            return (cell.getCellType() == CellType._NONE || cell.getCellType() == CellType.BLANK);
        }

    }


    public void readExcelUsingEventAPI() throws Exception {
        String filePath = "D:/Downloads/demo/src/main/resources/assets/mun.xlsx";
        IOUtils.setByteArrayMaxOverride(1000000000);
        try (OPCPackage pkg = OPCPackage.open(new FileInputStream(filePath))) {
            XSSFReader reader = new XSSFReader(pkg);
            XMLReader parser = null;
            try {

                parser = XMLReaderFactory.createXMLReader();
                SharedStringsTable shared = (SharedStringsTable) reader.getSharedStringsTable();
                MySheetHandler handler = new MySheetHandler(shared);
                parser.setContentHandler(handler); // Custom handler

                XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator)
                        reader.getSheetsData();
                while (iter.hasNext()) {
                    try (InputStream sheet = iter.next()) {
                        parser.parse(new InputSource(sheet));
                    }
                }
                List<List<String>> rows = handler.getData();
                System.out.println("row size " + rows.size());
                for (int i = 0; i < rows.size(); i++) {
                    System.out.println("rw " + rows.get(i));
                }
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static boolean isEmptyCell(Cell cell){
        if(cell == null || cell.getCellType() == CellType.BLANK){
            return true;
        }else{
            return false;
        }

    }
}
