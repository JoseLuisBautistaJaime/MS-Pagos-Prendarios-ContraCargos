

package mx.com.nmp.pagos.mimonte.util;


import java.text.NumberFormat;


/**
 * Nombre: FormatoUtil
 * Descripcion: Utileria para formatear numeros
 *
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */
public class FormatoUtil {

    /**
     * Constructor privado ya que solo contendra metodos estaticos
     */
    private FormatoUtil() {
        super();
    }

    /**
     * Convierte un numero String a formato decimal patron ###,###.##
     *
     * @param str Numero
     *
     * @return Numero con formato decimal, si el parametro es no numerico regresa el parametro tacual
     */
    public static String formatDecimalString(String str) {
        try {
            Number numero = Double.parseDouble(str);
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);

            return nf.format(numero);
        } catch (Exception e) {
            return str;
        }
    }
}
