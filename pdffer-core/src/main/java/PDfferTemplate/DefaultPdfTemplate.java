package PDfferTemplate;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import template.PdfTemplate;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class DefaultPdfTemplate implements PdfTemplate {
    private Map<String, Object> data;
    private byte[] output;

    @Override
    public Map<String, Object> getPdfData(){ return data;}

    @Override
    public void setPdfData(Map<String, Object> data){ this.data = data;}

    @Override
    public boolean validate(){ return true; }

    @Override
    public void generate(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document layoutDocument = new Document(pdfDocument);

        layoutDocument.setMargins(25f, 25f,25f,25f);

        Table table = new Table(UnitValue.createPercentArray( new float[] {47.5f, 5f, 47.5f}))
                .useAllAvailableWidth()
                .setMargins(0f,0f,0f,0f);
        Cell keyCell = new Cell().add(new Paragraph("KEY").setBold())
                .setBorder(Border.NO_BORDER);
        table.addHeaderCell(keyCell);
        table.addHeaderCell(new Cell().setBorder(Border.NO_BORDER));
        Cell valueCell = new Cell().add(new Paragraph("VALUE").setBold())
                .setBorder(Border.NO_BORDER);
        table.addHeaderCell(valueCell);

        for(Map.Entry<String, Object> pair: data.entrySet()) {
            Cell keyCell2 = new Cell().add(new Paragraph(pair.getKey()))
                    .setBorder(Border.NO_BORDER);
            table.addCell(keyCell2);
            table.addCell(new Cell().setBorder(Border.NO_BORDER));
            Cell valueCell2 = new Cell().add(new Paragraph(pair.getValue().toString()))
                    .setBorder(Border.NO_BORDER);
            table.addCell(valueCell2);
        }
        layoutDocument.add(table);
        layoutDocument.close();

        output = outputStream.toByteArray();
    }

    @Override
    public byte[] getPdfContent(){
        return output;
    }
}
