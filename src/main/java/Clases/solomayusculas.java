
package Clases;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;


public class solomayusculas extends javax.swing.text.PlainDocument {
  @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offs, str.toUpperCase(), a);
    }  
}
