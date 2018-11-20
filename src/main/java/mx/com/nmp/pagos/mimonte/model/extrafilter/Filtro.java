

package mx.com.nmp.pagos.mimonte.model.extrafilter;


/**
 * Enumeracion que contendra los filtros sobre extrafilter
 *
 */
public enum Filtro {
    /**
     * Filtra el catalogo de estatus resultado por el tipo de auditoria
     */
    ESTATUS_RESULTADO_TIPO_AUDITORIA(
            " INNER JOIN estatus_resultado_tipo_auditoria er_ta on cat.id = er_ta.id_estatus_resultado" +
            " WHERE er_ta.id_tipo_auditoria = ?"
    );

    /**
     * Consulta que contiene el filtro a aplicar
     */
    String query;

    /**
     * Contructor de la clase
     *
     * @param query Consulta con el filtro a aplicar
     */
    Filtro(String query) {
        this.query = query;
    }

    /**
     * Recupera el valor de {@code query}
     *
     * @return Valor de {@code query}
     */
    public String getQuery() {
        return query;
    }
}
