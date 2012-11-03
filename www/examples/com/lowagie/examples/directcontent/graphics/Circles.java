/*
 * $Id: Circles.java 3989 2009-06-18 02:22:54Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */
package com.lowagie.examples.directcontent.graphics;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Simple use of PdfContentByte: drawing Circles
 */
public class Circles {
    
    /**
     * Draws some concentric circles.
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        
        System.out.println("Drawing circles");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("circles.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we grab the ContentByte and do some stuff with it
            PdfContentByte cb = writer.getDirectContent();

            cb.circle(250.0f, 500.0f, 200.0f);
            cb.circle(250.0f, 500.0f, 150.0f);
            cb.stroke();
            cb.setRGBColorFill(0xFF, 0x00, 0x00);
            cb.circle(250.0f, 500.0f, 100.0f);
            cb.fillStroke();
            cb.setRGBColorFill(0xFF, 0xFF, 0xFF);
            cb.circle(250.0f, 500.0f, 50.0f);
            cb.fill();
            
            cb.sanityCheck();
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
    }
}