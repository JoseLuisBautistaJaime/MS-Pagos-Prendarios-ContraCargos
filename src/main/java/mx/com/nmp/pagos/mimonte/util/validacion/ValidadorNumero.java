
package mx.com.nmp.pagos.mimonte.util.validacion;


import org.springframework.util.Assert;


/**
 * Nombre: ValidadorNumero
 * Descripcion:  Clase de utilería para validar numeros.

 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public final class ValidadorNumero {

    /**
     * Constante con valor Cero.
     */
    private static final double CERO = 0.0;

    /**
     * Constructor, privado ya que no debe ser instanciada.
     */
    private ValidadorNumero() {
        super();
    }

    /**
     * Método que valida si {@code valor} es menor que cero.
     *
     * @param valor Valor numérico a validar.
     *
     * @throws IllegalArgumentException Cuando {@code valor} es menor que cero.
     */
    public static void menorQueCero(Number valor) {
        Assert.notNull(valor, "El valor no debe ser nulo");

        if (valor.doubleValue() < CERO) {
            throw new IllegalArgumentException("El valor no debe ser menor que 0.");
        }
    }

    /**
     * Método que valida si {@code valor} es mayor que cero.
     *
     * @param valor Valor numérico a validar.
     *
     * @throws IllegalArgumentException Cuando {@code valor} es menor o igual que cero.
     */
    public static void menorIgualQueCero(Number valor) {
        Assert.notNull(valor, "El valor no debe ser nulo");

        if (valor.doubleValue() <= CERO) {
            throw new IllegalArgumentException("El valor no debe ser menor o igual que 0.");
        }
    }
}
