/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package sudoku.view.cell;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.text.NumberFormat;

public class CellInputLimiter extends PlainDocument {
    private JFormattedTextField jFormattedTextField;
    private int lengthLimit = 1;

    CellInputLimiter(){
        super();
        this.jFormattedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        jFormattedTextField.setDocument(this);
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(str==null){
            return;
        }
        if (str.matches("\\d+")){
            if((getLength() + str.length() <= lengthLimit) && !str.equals("0")){
                super.insertString(offs,str,a);
            }
        }
    }
}
