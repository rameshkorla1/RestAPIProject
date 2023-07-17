package reusable;

	import java.io.FileInputStream;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	    public static List<Map<String, Object>> readTestDataFromExcel(String filePath, String sheetName) {
	        List<Map<String, Object>> testDataList = new ArrayList<>();

	        try (FileInputStream file = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(file)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);

	            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	                Row currentRow = sheet.getRow(rowIndex);
	                Map<String, Object> testData = new HashMap<>();

	                for (int colIndex = 0; colIndex < headerRow.getLastCellNum(); colIndex++) {
	                    Cell headerCell = headerRow.getCell(colIndex);
	                    Cell currentCell = currentRow.getCell(colIndex);

	                    testData.put(headerCell.getStringCellValue(), getCellValue(currentCell));
	                }

	                testDataList.add(testData);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return testDataList;
	    }

	    private static Object getCellValue(Cell cell) {
	    	switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                return cell.getDateCellValue();
	            } else {
	                return cell.getNumericCellValue();
	            }
	        case BOOLEAN:
	            return cell.getBooleanCellValue();
	        default:
	            return null;
	    }
	}
}

