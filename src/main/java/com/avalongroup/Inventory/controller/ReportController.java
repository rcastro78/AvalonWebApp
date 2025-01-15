package com.avalongroup.Inventory.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


    @RestController
    public class ReportController {
        @Autowired
        private DataSource dataSource; // Spring Boot configura automáticamente el DataSource

        @GetMapping("/generateReport")
        public ResponseEntity<byte[]> generateReport(@RequestParam String idDist) throws Exception {
            // Ruta del archivo .jasper (archivo compilado)
            String jasperPath = "reports/TAG_LineList2.jasper"; // Cambia esto a la ruta de tu archivo .jasper

            // Cargar el archivo .jasper desde el classpath
            ClassPathResource resource = new ClassPathResource(jasperPath);
            InputStream jasperInputStream = resource.getInputStream();

            // Cargar el reporte compilado (.jasper)
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperInputStream);

            // Configurar parámetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("idDist", idDist); // Pasar el parámetro dinámico

            // Llenar el reporte con los datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource.getConnection()
            );

            // Exportar el reporte a PDF usando ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);

            // Obtener los bytes del archivo PDF generado
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

            // Devolver el archivo PDF como una respuesta HTTP con los encabezados adecuados
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfBytes.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                    .body(pdfBytes);
        }

        @GetMapping("/wholesomeReport")
        public ResponseEntity<byte[]> wholesomeReport(@RequestParam String idDist) throws Exception {
            // Ruta del archivo .jasper (archivo compilado)
            String jasperPath = "reports/TAG_LineListWholesome.jasper"; // Cambia esto a la ruta de tu archivo .jasper

            // Cargar el archivo .jasper desde el classpath
            ClassPathResource resource = new ClassPathResource(jasperPath);
            InputStream jasperInputStream = resource.getInputStream();

            // Cargar el reporte compilado (.jasper)
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperInputStream);

            // Configurar parámetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("idDist", idDist); // Pasar el parámetro dinámico

            // Llenar el reporte con los datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource.getConnection()
            );

            // Exportar el reporte a PDF usando ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);

            // Obtener los bytes del archivo PDF generado
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

            // Devolver el archivo PDF como una respuesta HTTP con los encabezados adecuados
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfBytes.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                    .body(pdfBytes);
        }

    }