package com.mindhub.Homebanking.models;



import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Ejemplo {
    private List<Transaction> listTransaction;

    public Ejemplo(List<Transaction> listTransaction){
            this.listTransaction= listTransaction;
    }
    private void writeTableHeader(PdfPTable table) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.BLUE);
            cell.setPadding(5);

                Font font = FontFactory.getFont(FontFactory.HELVETICA);
                font.setColor(Color.WHITE);

                cell.setPhrase(new Phrase("Transaction ID", font));
                table.addCell(cell);

                cell.setPhrase(new Phrase("type", font));
                table.addCell(cell);

                cell.setPhrase(new Phrase("amount", font));
                table.addCell(cell);

                cell.setPhrase(new Phrase("description", font));
                table.addCell(cell);

                cell.setPhrase(new Phrase("date", font));
                table.addCell(cell);
    }
    private void writeTableData(PdfPTable table) {
        for (Transaction transaction : listTransaction) {
                table.addCell(String.valueOf(transaction.getId()));
                table.addCell(transaction.getType().toString());
                table.addCell(transaction.getAmount().toString());
                table.addCell(transaction.getDescription());
                table.addCell(transaction.getDate().toString());
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());

                document.open();
                Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                font.setSize(18);
                font.setColor(Color.BLUE);

                Paragraph p = new Paragraph("List of Users", font);
                p.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(p);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100f);
                table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
                table.setSpacingBefore(10);

                writeTableHeader(table);
                writeTableData(table);

                document.add(table);
                document.close();

    }
    }
