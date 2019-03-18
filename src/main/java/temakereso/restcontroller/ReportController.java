package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.ReportData;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicFilters;
import temakereso.service.ReportService;
import temakereso.service.implementation.ExcelGeneratorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    // ------------------------ GET -------------------------- //

    @GetMapping(path = "/reports/general", produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> getGeneralReport(TopicFilters filters) throws IOException {
        ReportData reportData = reportService.findTopics(filters);

        ExcelGeneratorService excelGenerator = new ExcelGeneratorService(reportData.getHeader(), reportData.getData());
        byte[] file = excelGenerator.generate();

        return generateExcel(file, "Általános riport.xlsx");
    }

    @GetMapping(path = "/reports/departments", produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> getDepartmentReport(ReportFilters filters) throws IOException {
        ReportData reportData = reportService.findTopicsByDepartment(filters);

        ExcelGeneratorService excelGenerator = new ExcelGeneratorService(reportData.getHeader(), reportData.getData());
        byte[] file = excelGenerator.generate();

        return generateExcel(file, "Riport tanszék szerinti bontásban.xlsx");
    }

    @GetMapping(path = "/reports/categories", produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> getCategoryReport(ReportFilters filters) throws IOException {
        ReportData reportData = reportService.findTopicsByCategory(filters);

        ExcelGeneratorService excelGenerator = new ExcelGeneratorService(reportData.getHeader(), reportData.getData());
        byte[] file = excelGenerator.generate();

        return generateExcel(file, "Riport kategória szerinti bontásban.xlsx");
    }


    @GetMapping(path = "/reports/types", produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> getTypeReport(ReportFilters filters) throws IOException {
        ReportData reportData = reportService.findTopicsByType(filters);

        ExcelGeneratorService excelGenerator = new ExcelGeneratorService(reportData.getHeader(), reportData.getData());
        byte[] file = excelGenerator.generate();

        return generateExcel(file, "Riport típus szerinti bontásban.xlsx");
    }

    private ResponseEntity<ByteArrayResource> generateExcel(byte[] file, String excelFileName) {
        ByteArrayResource resource = new ByteArrayResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + excelFileName);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length)
                // .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
