package ro.agitman.ing.model;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by edi on 27.08.15.
 */
public class XslProcessing {

    private static final XslProcessing instance = new XslProcessing();

    private XslProcessing() {
    }

    public static XslProcessing getInstance() {
        return instance;
    }


    public List<Transaction> parseFile(InputStream stream) {
        List<Transaction> result = new ArrayList<>();
        NPOIFSFileSystem fs = null;

        try {
            fs = new NPOIFSFileSystem(stream);
            HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
            HSSFSheet sheet = wb.getSheetAt(0);

            Transaction transaction = null;

            for (Row row : sheet) {

                Cell dateCell = row.getCell(1);
                if (dateCell.getCellType() == 3) {
                    //build current transaction
                    if (transaction != null) {
                        String data = row.getCell(3).getStringCellValue();
                        if (data.startsWith("Terminal: ")) {
                            transaction.setTerminal(data.substring("Terminal: ".length()));
                        }

                        if (data.startsWith("Nr. card: ")) {
                            transaction.setNoCard(data.substring("Nr. card: ".length()));
                        }

                        if (data.startsWith("Suma: ")) {
                            String[] sum = data.substring("Suma: ".length()).trim().split(" ");
                            transaction.setAmount(parseValue(sum[0]));
                            transaction.setCurrency(sum[1]);
                        }
                    }

                } else {
                    if (transaction != null) {
                        result.add(transaction);
                    }
                    //build new transaction
                    transaction = new Transaction();

                    String dateStr = dateCell.getStringCellValue().trim();
                    String[] dateToken = dateStr.split(" ");
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt(dateToken[2]),
                            Months.getMonthIndex(dateToken[1]),
                            Integer.parseInt(dateToken[0]), 0, 0, 0);

                    transaction.setOpDate(calendar.getTime());
                    transaction.setName(row.getCell(3).getStringCellValue());

                    transaction.setDebit(row.getCell(5).getCellType() != 3);
                    if (transaction.getDebit()) {
                        Cell numberCell = row.getCell(5);
                        String value = numberCell.getStringCellValue();
                        transaction.setAmountRon(parseValue(value));
                    } else {
                        Cell numberCell = row.getCell(7);
                        String value = numberCell.getStringCellValue();
                        transaction.setAmountRon(parseValue(value));
                    }
                }
            }

            fs.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private BigDecimal parseValue(String value){
        value = value.replace(".", "").replace(",", ".");
        return new BigDecimal(value);
    }

}
