package com.avalongroup.Inventory.service;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {

    public byte[] generateReport(Map<String, Object> parameters, List<?> dataSource) throws JRException {
        // Carga el reporte
        InputStream reportStream = getClass().getResourceAsStream("/reports/TAG_LineList.jasper");

        // Verifica si el archivo existe
        if (reportStream == null) {
            throw new IllegalArgumentException("Reporte no encontrado");
        }

        // Crea el datasource con los datos
        JRBeanCollectionDataSource dataSourceBean = new JRBeanCollectionDataSource(dataSource);

        // Llena el reporte con los datos y parámetros
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, dataSourceBean);

        // Exporta el reporte a PDF y devuelve el contenido como bytes
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
