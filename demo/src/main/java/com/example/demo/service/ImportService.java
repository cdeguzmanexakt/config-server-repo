package com.example.demo.service;

import com.example.demo.model.Municipality;
import com.example.demo.repo.MunisipalityRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {
    private MunisipalityRepository muniRepo;

    public ImportService(MunisipalityRepository muniRepo) {
        this.muniRepo = muniRepo;
    }

    public boolean importMuni(){
        List<Municipality> muniList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("D:/Downloads/demo/src/main/resources/assets/mun.xlsx");
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            if(sheet != null) {
                for(Row row : sheet) {
                    if(row.getRowNum()>0){
                        if(410 == row.getCell(4).getNumericCellValue()){
                            try {
                                System.out.println(row.getCell(2));
                                Municipality muni = new Municipality();
                                muni.setProvCode((int) row.getCell(4).getNumericCellValue());
                                muni.setCitymunCode((int) row.getCell(5).getNumericCellValue());
                                muni.setProvDesc(row.getCell(2).getStringCellValue());
                                muni.setCitymunDesc("BATANGAS");
                                muniList.add(muni);

                            } catch (Exception e) {
                                System.out.println("test");
                            }
                        }
                    }

                }
                muniRepo.saveAll(muniList);
                return true;
            }else {
                System.out.println("sheet null");
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public List<Municipality> getAllMunicipality(){
        return muniRepo.findAll();
    }
}
