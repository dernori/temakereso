package temakereso.service.implementation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import temakereso.helper.HeaderData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelGeneratorService {

    private List<HeaderData> headers;
    private List<List<List<Object>>> data;

    private Workbook workbook = null;
    private List<Sheet> sheets = null;

    public ExcelGeneratorService(List<HeaderData> headers, List<List<List<Object>>> data) {
        this.headers = headers;
        this.data = data;
    }

    public byte[] generate() throws IOException {
        init();

        for (int sheetIndex = 0; sheetIndex < headers.size(); sheetIndex++) {
            createHeader(sheetIndex);
            writeData(sheetIndex);
            autoSizeColumns(sheetIndex);
        }
        return writeWbToOutput();
    }

    private void writeData(int sheetIndex) {
        //0 -> header
        int rowIndex = 1;

        Sheet sheet = sheets.get(sheetIndex);
        List<List<Object>> sheetData = data.get(sheetIndex);

        for (List<Object> rowData : sheetData) {
            Row row = sheet.createRow(rowIndex++);

            for (int i = 0; i < rowData.size(); i++) {
                Cell cell = row.createCell(i);

                Object value = rowData.get(i);
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                } else if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else {
                    cell.setCellValue((String) value);
                }
            }

        }
    }

    private byte[] writeWbToOutput() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return bos.toByteArray();
    }


    private void autoSizeColumns(int sheetIndex) {
        for (int j = 0; j < headers.get(sheetIndex).getColumns().size(); j++) {
            sheets.get(sheetIndex).autoSizeColumn(j);
        }
    }

    private void init() {
        workbook = new XSSFWorkbook();
        sheets = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            sheets.add(workbook.createSheet(headers.get(i).getSheetName()));
        }
    }

    private void createHeader(int sheetIndex) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setFontName("Calibri");

        byte[] dark = new byte[]{(byte) 135, 77, 99};
        XSSFColor bgColor = new XSSFColor(dark, null);

        byte[] light = new byte[]{0, 0, 0};
        XSSFColor fgColor = new XSSFColor(light, null);

        XSSFCellStyle headerCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(bgColor);
        headerCellStyle.setFillBackgroundColor(fgColor);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //create header shelf
        Row headerRow = sheets.get(sheetIndex).createRow(0);

        List<String> columns = headers.get(sheetIndex).getColumns();
        for (int i = 0; i < columns.size(); i++) {

            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
            cell.setCellStyle(headerCellStyle);
        }
    }

}
