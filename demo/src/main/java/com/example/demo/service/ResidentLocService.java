package com.example.demo.service;

import com.example.demo.model.Municipality;
import com.example.demo.model.Resident;
import com.example.demo.repo.ResidentRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentLocService {

    private ResidentRepository resRepo;

    public ResidentLocService(ResidentRepository resRepo) {
        this.resRepo = resRepo;
    }

    public List<Resident> findResByBrgy(int brgyCode){
       return resRepo.findByBrgyCode(brgyCode);
    }

    public boolean importResident(){
        List<Resident> residentList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("D:/Downloads/demo/src/main/resources/assets/mun1.xlsx");
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(1);
            if(sheet != null) {
                for(Row row : sheet)
                    if (row.getRowNum() > 0) {
                        try {
                            Resident res = new Resident();
                            ;
//                            System.out.println(row.getCell(0).getNumericCellValue());
                            res.setMobileNum(BigDecimal.valueOf(row.getCell(0).getNumericCellValue()).toPlainString());
                            res.setFirstName(row.getCell(1).getStringCellValue());
                            res.setLastName(row.getCell(2).getStringCellValue());
                            res.setVbFlag(false);
                            res.setVoter(true);
                            res.setBrgyCode((int) row.getCell(8).getNumericCellValue());
                            res.setMuniCode((int) row.getCell(9).getNumericCellValue());
                            residentList.add(res);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                resRepo.saveAll(residentList);
                System.out.println("size " + residentList.size());
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
}
